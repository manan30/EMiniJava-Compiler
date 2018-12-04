import java_cup.runtime.Symbol;
import lexer.Lexer;
import lexer.SymbolMapping;

import java.io.*;

public class CompilerMain {

    CompilerMain() {

    }

    public static void main(String[] args) throws IOException {

        CompilerMain main = new CompilerMain();

        if (args.length < 2) {

            if (!(args.length == 1)) {

                System.out.println("USAGE: java -jar (Main JAR File) <options> <filenames>");
                System.exit(0);

            }

        }

        String options = args[0];

        switch (options) {

            case "--lex":
                main.lexer(args);
                break;

            /*case "--ast":
                main.parser(args);
                break;

            case "--name":
                main.nameAnalyser(args);
                break;

            case "--pp":
                main.prettyPrinter(args);
                break;

            case "--varType":
                main.typeAnalysis(args);
                break;

            case "--completeTest":
                main.completeTest(args[1]);
                break;

            case "--cgen":
                main.codeGenerator(args);
                break;

            case "--cfg":
                main.cfgGenerator(args);
                break;
            case "--opt":
                main.opt(args);
                break;*/

            case "--help":
                System.out.println("Available options:");
                System.out.println("--lex");
                System.out.println("--ast");
                System.out.println("--name");
                System.out.println("--pp");
                System.out.println("--varType");
                System.out.println("--cgen");
                System.out.println("--cfg");
                System.out.println("--opt");

                System.exit(0);

            default:
                System.out.println("Invalid option");
                System.out.println("Available options:");
                System.out.println("--lex");
                System.out.println("--ast");
                System.out.println("--name");
                System.out.println("--pp");
                System.out.println("--varType");
                System.out.println("--help");
                System.out.println("--cgen");
                System.out.println("--cfg");
                System.out.println("--opt");
                System.exit(0);

        }

    }

    private void lexer(String[] filenames) throws IOException {

        if (filenames.length == 1) {
            System.out.println("Please specify at least 1 input file.");
            System.exit(0);
        }

        FileReader reader = null;
        FileWriter writer = null;

        for (int i = 1; i < filenames.length; i++) {

            String fileExt = filenames[i].substring(filenames[i].lastIndexOf('.') + 1);

            if (!fileExt.equals("emj")) {

                System.err.println("Invalid file extension.");
                System.exit(0);

            }

            String filePath = new File("").getAbsolutePath();
            File inputFile = new File(filePath + File.separator + filenames[i]);

            if (inputFile.exists() && inputFile.canRead())
                System.out.println("Lexing File :: " + filenames[i]);

            try {

                reader = new FileReader(inputFile);
                writer = new FileWriter(filenames[i].substring(0, filenames[i].lastIndexOf(".")) + ".lexed");

                Lexer lexer = new Lexer(reader);

                Symbol symbol = lexer.next_token();

                while (symbol != null && symbol.sym != SymbolMapping.symbolsMap.get("EOF")) {

                    writer.write(symbol.toString());
                    writer.write("\n");

                    symbol = lexer.next_token();

                    if (symbol.sym == SymbolMapping.symbolsMap.get("EOF")) {
                        writer.write(symbol.toString());
                        break;
                    }

                }

                System.out.println("File Created :: " + filenames[i].substring(0, filenames[i].lastIndexOf(".")) + ".lexed");

                if (filenames.length > 2) {
                    System.out.println();
                    System.out.println("###########################");
                    System.out.println();
                }

            } catch (FileNotFoundException e) {
                System.err.println(filenames[i] + " - File not found!");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Please check the input parameters.");
            } finally {
                if (writer != null)
                    writer.close();
                if (reader != null)
                    reader.close();
            }

        }

    }

}
