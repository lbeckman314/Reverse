package com.liambeckman.reverse;

import java.io.IOException;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.Vector;
import java.io.FileInputStream;

import org.apache.commons.cli.*;
import org.apache.commons.text.WordUtils;


public class Reverse {
    public static void main(String[] args) {
        Vector<Protein> proteins = new Vector<Protein>();

        Options options = new Options();
        Option infile_opt = new Option("i", "input", true, "input file (default: dummy.fasta in target/reverse-*.jar).");
        options.addOption(infile_opt);

        Option outfile_opt = new Option("o", "output", true, "output file (default: rev.fasta).");
        options.addOption(outfile_opt);

        Option line_length_opt = new Option("l", "linelength", true, "length of line in output (default: 80).");
        options.addOption(line_length_opt);

        Option verbose_opt = new Option("v", "verbose", false, "Be verbose (default: false).");
        options.addOption(verbose_opt);

        Option help_opt = new Option("h", "help", false, "Print help message.");
        options.addOption(help_opt);

        CommandLineParser parser = new DefaultParser();
        CommandLine cmd;
        String infile = "dummy.fasta";
        boolean classpath = true;
        String outfile = "rev.fasta";
        int line_length = 80;
        boolean verbose = false;

        try {
            cmd = parser.parse(options, args);
            if (cmd.hasOption("input")) {
                infile = cmd.getOptionValue("input");
                classpath = false;
            }
            if (cmd.hasOption("output")) {
                outfile = cmd.getOptionValue("output");
            }
            if (cmd.hasOption("linelength")) {
                line_length = Integer.parseInt(cmd.getOptionValue("linelength"));
            }
            if (cmd.hasOption("verbose")) {
                verbose = true;
            }
            if (cmd.hasOption("help")) {
                HelpFormatter formatter = new HelpFormatter();
                formatter.printHelp("Reverse", options);
            }

        } catch (ParseException err) {
            System.out.println(err.getMessage());
        }

        if (verbose) {
            System.out.println("infile: " + infile);
            System.out.println("outfile: " + outfile);
            System.out.println("line_length: " + line_length);
            System.out.println("verbose: " + verbose);
        }

        proteins = protein_reverse(infile, classpath);

        // write reversed protein to file.
        clear_file(outfile);
        protein_write(proteins, outfile, line_length, verbose);
    }

    public static Vector<Protein> protein_reverse(String filename, boolean classpath) {
        Vector<Protein> proteins = new Vector<Protein>();

        try {
            // create stream and reader from filename.
            InputStream input;
            if (classpath) {
                input = Reverse.class.getClassLoader().getResourceAsStream(filename);
            }
            else {
                input = new FileInputStream(filename);
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(input));

            // used in ">REV_00000X reversed" output line.
            int protein_num = 0;

            // read all lines in file.
            while(reader.ready()) {
                String id = "";
                String amino_acids = "";

                // read id line
                String line = reader.readLine();

                if (line.charAt(0) == '>') {

                    // read amino acid line
                    line = reader.readLine();
                    while (line != null && line.charAt(0) != '>') {
                        amino_acids += line;
                        line = reader.readLine();
                    }

                    // create id (e.g. ">REV_000001 reversed")
                    protein_num += 1;
                    id = String.format("%1$" + 6 + "s", Integer.toString(protein_num)).replace(' ', '0');
                    id = ">REV_" + id + " reversed";

                    // create reversed amino_acid sequence.
                    String amino_acids_reversed = new StringBuilder(amino_acids).reverse().toString();

                    Protein protein = new Protein(id, amino_acids_reversed);
                    proteins.add(protein);
                }
            }
        }
        catch(Exception err) {
            err.printStackTrace();
        }

        return proteins;
    }

    // write protein sequence to file.
    public static void protein_write(Vector<Protein> proteins, String filename, int line_length, boolean verbose) {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(filename, true));
            for (Protein protein : proteins) {
                writer.write(protein.id);
                writer.newLine();
                String amino_acids_wrapped = WordUtils.wrap(protein.amino_acids, line_length, null, true);
                writer.write(amino_acids_wrapped);
                writer.newLine();

                if (verbose) {
                    System.out.println(protein.id);
                    System.out.println(amino_acids_wrapped);
                }
            }
            writer.close();

        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    public static void clear_file(String filename) {
        BufferedWriter writer;
        try {
            writer = new BufferedWriter(new FileWriter(filename));
            writer.write("");
            writer.close();
        } catch (IOException err) {
            err.printStackTrace();
        }
    }
}
