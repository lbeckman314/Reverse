package com.liambeckman.reverse;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import java.util.Vector;

/**
 * Unit test for simple Reverse.
 */
public class ReverseTest
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public ReverseTest(String myTest)
    {
        super(myTest);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite(ReverseTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testReverse()
    {
        Vector<Protein> proteins = new Vector<Protein>();
        String infile = "src/main/resources/dummy.fasta";
        boolean classpath = false;
        proteins = Reverse.protein_reverse(infile, classpath);

        // test id's.
        for (int i = 0; i < 5; i++) {
            String id = proteins.get(i).id;

            String template = ">REV_00000%s reversed";
            String result = String.format(template, Integer.toString(i + 1));

            assertTrue(id.equals(result));
        }

        // test amino acid reversal.
        String original_1 = "AGWSCLVTGGGGFLGQRIICLLVEEKDLQEIRVLDKVFRPEVREEFSKLQSKIKLTLLEGDILDEQCLKGACQGTSVVIHTASVIDVRNAVPRETIMNVNVKGTQLLLEACVQASVPVFIHTSTIEVAGPNSYREIIQDGREEEHHESAWSSPYPYSKKLAEKAVLGANGWALKNGGTLYTCALRPMYIYGEGSPFLSAYMHGALNNNGILTNHCKFSRVNPVYVGNVAWAHILALRALRDPKKVPNIQGQFYYISDDTPHQSYDDLNYTLSKEWGFCLDSRMSLPISLQYWLAFLLEIVSFLLSPIYKYNPCFNRHLVTLSNSVFTFSYKKAQRDLGYEPLYTWEEAKQKTKEWIGSLVKQHKETLKTKIH";

        String amino_acids_reversed = new StringBuilder(proteins.get(0).amino_acids).reverse().toString();
        assertTrue(original_1.equals(amino_acids_reversed));

        String original_5 = "MAAGAVVGAWMLVLSLGGTVTGDQNITARIGKPLVLNCKGAPKKPPQQLEWKLNTGRTEAWKVLSPQGDPWDSVARVLPNGSLLLPAVGIQDEGTFRCRATSRSGKETKSNYRVRVYQIPGKPEIVDPASELMAGVPNKVGTCVSEGGYPAGTLNWLLDGKTLIPDGKGVSVKEETKRHPKTGLFTLHSELMVTPARGGALHPTFSCSFTPGLPRRRALHTAPIQLRVWSEHRGGEGPNVDAVPLKEVQLVVEPEGGAVAPGGTVTLTCEAPAQPPPQIHWIKDGRPLPLPPGPMLLLPEVGPEDQGTYSCVATHPSHGPQESRAVSVTIIETGEEGTTAGSVEGPGLETLALTLGILGGLGTVALLIGVIVWHRRRQRKGQERKVPENQEEEEEERAELNQPEEPEAAESSTGGP";

        amino_acids_reversed = new StringBuilder(proteins.get(4).amino_acids).reverse().toString();
        assertTrue(original_5.equals(amino_acids_reversed));

        String original_10 = "GRVGGRVGSTPPWKGPKKHKLTESEHTVVLTVTGEPCHFPFQYHRQLHHKCIHRGRPGPQPWCATTPNFEKDQRWAYCLEPKKVKDHCSKHNPCQKGGTCVNMPDGPRCICADHFTGKHCQKEKCFEPQFFRFFHENEIWHRLEPAGVVKCQCKGPNAQCKPLASQVCRTNPCLNGDSCLQAEGHRLCRCAPSFAGRLCDVDLKASCYDDRDRGLSYRGMAGTTLSGAPCQSWASEATYWNVTAEQVLNWGLGDHAFCRNPDNDTRPWCFIWKGDRLSWNYCRLAPCQAAAGHEHFPLPSPSALQKPESTTQTPLPSLTSGWCSPTPLASGGPGGCGQRLRKWLSSLNRVVGGLVALPGAHPYIAALYWDQHFCAGSLIAPCWVLTAAHCLQNRPAPKELTVVLGQDRHNQSCEQCQTLAVRDYRLHEAFSPITYQHDLALVRLQESADGCCAHPSPFVQPVCLPSTAARPAESEAAVCEVAGWGHQFEGGEYSSFLQEAQVPLIDPQRCSAPDVHGAAFTQGMLCAGFLEGGTDACQGDSGGPLVCEDETPERQLILRGIVSWGSGCGNRLKPGVYTDVANYLAWIREHTAS";

        System.out.println("SIZE: " + proteins.size());
        amino_acids_reversed = new StringBuilder(proteins.get(9).amino_acids).reverse().toString();

        assertTrue(original_10.equals(amino_acids_reversed));
    }
}
