[![Build Status](https://travis-ci.org/lbeckman314/Reverse.svg?branch=master)](https://travis-ci.org/lbeckman314/Reverse)

# Reverse

This java project reverses amino acid sequences in proteins! The "main" source file is [Reverse.java](./src/main/java/com/liambeckman/reverse/Reverse.java).

* [Quick Start](#quick-start)
  + [Requirements](#requirements)
  + [Running](#running)
* [Options](#options)
* [Example](#example)
  + [Input](#input)
  + [Output](#output)
* [Development](#development)
  + [Requirements](#requirements-1)
* [CI/CD](#cicd)

## Quick Start

```sh
chmod +x run.sh
./run.sh
```

### Requirements

- [Java Runtime Environment 1.8+](https://openjdk.java.net/install/index.html)

### Running

A small script called [run.sh](./run.sh) is provided to run the program:

```sh
java -cp "target/*" com.liambeckman.reverse.Reverse --verbose
```

To start `Reverse`, enter:

```sh
git clone https://github.com/lbeckman314/Reverse
cd Reverse
chmod +x run.sh
./run.sh
```

## Options

```sh
java -cp "target/*" com.liambeckman.reverse.Reverse --help
usage: Reverse
 -h,--help               Print help message.
 -i,--input <arg>        input file (default: dummy.fasta in
                         target/reverse-*.jar).
 -l,--linelength <arg>   length of line in output (default: 80).
 -o,--output <arg>       output file (default: rev.fasta).
 -v,--verbose            Be verbose (default: false).

```

## Example

### Input

```
>3BHS_BOVIN (P14893) 3 beta-hydroxysteroid dehydrogenase/delta 5-->4-isomerase (3Beta-HSD) [Includes: 3-beta-hydroxy-delta(5)-steroid dehydrogenase (EC 1.1.1.145) (3-beta-hydroxy-5-ene steroid dehydrogenase) (Progesterone reductase); Steroid delta-isomera
AGWSCLVTGGGGFLGQRIICLLVEEKDLQEIRVLDKVFRPEVREEFSKLQSKIKLTLLEG
DILDEQCLKGACQGTSVVIHTASVIDVRNAVPRETIMNVNVKGTQLLLEACVQASVPVFI
HTSTIEVAGPNSYREIIQDGREEEHHESAWSSPYPYSKKLAEKAVLGANGWALKNGGTLY
TCALRPMYIYGEGSPFLSAYMHGALNNNGILTNHCKFSRVNPVYVGNVAWAHILALRALR
DPKKVPNIQGQFYYISDDTPHQSYDDLNYTLSKEWGFCLDSRMSLPISLQYWLAFLLEIV
SFLLSPIYKYNPCFNRHLVTLSNSVFTFSYKKAQRDLGYEPLYTWEEAKQKTKEWIGSLV
KQHKETLKTKIH
```

### Output

```
>REV_000001 reversed
HIKTKLTEKHQKVLSGIWEKTKQKAEEWTYLPEYGLDRQAKKYSFTFVSNSLTVLHRNFCPNYKYIPSLLFSVIELLFAL
WYQLSIPLSMRSDLCFGWEKSLTYNLDDYSQHPTDDSIYYFQGQINPVKKPDRLARLALIHAWAVNGVYVPNVRSFKCHN
TLIGNNNLAGHMYASLFPSGEGYIYMPRLACTYLTGGNKLAWGNAGLVAKEALKKSYPYPSSWASEHHEEERGDQIIERY
SNPGAVEITSTHIFVPVSAQVCAELLLQTGKVNVNMITERPVANRVDIVSATHIVVSTGQCAGKLCQEDLIDGELLTLKI
KSQLKSFEERVEPRFVKDLVRIEQLDKEEVLLCIIRQGLFGGGGTVLCSWGA
```

## Development

### Requirements

- [maven](https://maven.apache.org/)
- [Java Development Kit 1.8+](https://openjdk.java.net/install/index.html)
- [Git](https://git-scm.com/)

To compile and run source files, enter:

```sh
mvn compile && ./run.sh
```

## CI/CD

A simple test suite is defined in [ReverseTest.java](./src/test/java/com/liambeckman/reverse/ReverseTest.java). It's invoked with `mvn verify` in the [.travis.yml](./.travis.yml) file upon every push to the `master` branch.
 
Build results may be viewed at [travis-ci.org](https://travis-ci.org/lbeckman314/Reverse)

[![Screenshot of Travis CI](./build.png)](https://travis-ci.org/lbeckman314/Reverse)
