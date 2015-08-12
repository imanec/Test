/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test4;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.ling.CoreAnnotations.NamedEntityTagAnnotation;
import edu.stanford.nlp.ling.CoreAnnotations.TokensAnnotation;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.PrintStream;
import java.io.Writer;
import java.util.Scanner;

public class NERTest {

private static StanfordCoreNLP pipeline;

	
	private static NERTest _instance = new NERTest();

	// constructor
	private NERTest() {
		// a StanfordCoreNLP object, with POS tagging, lemmatization, NER
		Properties props = new Properties();
		props.put("annotators",
				"tokenize, ssplit, pos, lemma, ner");
		pipeline = new StanfordCoreNLP(props);
	}

	public static NERTest getInstance() {
		return _instance;
	}
		
public static String quickNerAnnotator(String text) throws FileNotFoundException{
		
                int sentenceCounter=0;
                int locationCounter=0;
                int organizationCounter=0;
                int personCounter=0;
                int dateCounter=0;
                int namedEntitiesCounter=0;
                Annotation document = new Annotation(text);
			String rel = new String() ;
			Set<String> Ner = new HashSet<String>();
		// run all Annotators on this text
		pipeline.annotate(document);
		for (CoreLabel token : document.get(TokensAnnotation.class)) {
			
			String pos = token.get(NamedEntityTagAnnotation.class);
                        if(pos.equalsIgnoreCase("location")){
                            locationCounter++;
                            namedEntitiesCounter++;
                            
                        }
                        if(pos.equalsIgnoreCase("date")){
                            dateCounter++;
                            namedEntitiesCounter++;
                        }
                        if(pos.equalsIgnoreCase("person")){
                            personCounter++;
                            namedEntitiesCounter++;
                        }
                        if(pos.equalsIgnoreCase("organization")){
                            organizationCounter++;
                            namedEntitiesCounter++;
                        }
			String nerelement = text.substring(token.beginPosition(), token.endPosition());
			rel += nerelement +" : "+ pos + "\n";
                        
                		}		
                        
                        System.out.println("Number of named entities: "+namedEntitiesCounter);
                        System.out.println("Number of locations: "+locationCounter);
                        System.out.println("Number of people: "+personCounter);
                        System.out.println("Number of dates: "+dateCounter);
                        System.out.println("Number of organizations: "+organizationCounter);
                        writeFile2("Number of named entities: "+namedEntitiesCounter);
                        writeFile2("Number of locations: "+locationCounter);
                        writeFile2("Number of people: "+personCounter);
                        writeFile2("Number of dates: "+dateCounter);
                        writeFile2("Number of organizations: "+organizationCounter);
		
		return rel;
	}
	
public static void main(String[] args) throws IOException {
	
	NERTest test = new NERTest();
	
	File dir = new File("C:/Users/Imane/Documents/folders");
	File[] files = dir.listFiles();
	for (File file : files) {
		String text = FileTextIO.readFile(file);
                //String text = FileTextIO.readFile(dir);
		System.out.println(test.quickNerAnnotator(text));
                writeFile(test.quickNerAnnotator(text));
                //writeFile("\n");
                countSentences();
                
	}
}
public static void writeFile(String text) throws FileNotFoundException{
   PrintStream out = null;
    try {
        out = new PrintStream(new FileOutputStream("C:/Users/Imane/Documents/output.txt", true));
        out.println(text);
    }
    finally {
    if (out != null) {
        out.close();
    }
}
}

public static void writeFile2(String text) throws FileNotFoundException{
   PrintStream out = null;
    try {
        out = new PrintStream(new FileOutputStream("C:/Users/Imane/Documents/out.txt", true));
        out.println(text);
    }
    finally {
    if (out != null) {
        out.close();
    }
}
}


public static void countSentences() throws FileNotFoundException {
		int sentenceCounter = 0;
		/* Constructor to locate the file in the current directory. */
		try{File file=new File("C:/Users/Imane/Documents/folders/info.txt");
		/* Check if the file exists or not. */
		if(file.exists()){
			/* Check if the file is empty by check the length of the file.
			 * If it is empty then exit the program.
			 */
			if(file.length()==0){
				System.out.println("File is empty.");
				System.exit(-1);
			}
			else{
				String sentence="";
				int spaceCounter=0;
				int otherCounter=0;
				Scanner infile = new Scanner(file);
				file=new File("C:/Users/Imane/Documents/folders/info.txt");
				infile = new Scanner(file);
				while ((infile.hasNext()) )
				{
					String eachWord = infile.next();
					for (int i=0;i<eachWord.length();i++)
					{
						char breaktoLetters = eachWord.charAt(i);
						if (breaktoLetters ==(char)32){
							spaceCounter++;
						}else{otherCounter++;}
					}
				}
				if (otherCounter==0){
					System.out.println("The file only contain spaces. This will still be consistered empty. Goodbye!");
                                          System.exit(-1);  

				}
				

				/* Close the text file and open back up */
				infile.close();
				file=new File("C:/Users/Imane/Documents/folders/info.txt");
				infile = new Scanner(file);

				/* Sentence Counter START */
				while ((infile.hasNext()) )                                  
				{
					String eachWord = infile.next();
                                        sentence= sentence + eachWord +" ";
					for (int i=0;i<eachWord.length();i++)
					{
						char breaktoLetters = eachWord.charAt(i);
						if (breaktoLetters =='!'||breaktoLetters =='?'||breaktoLetters =='.')
						{
							sentenceCounter++;
                                                        System.out.println("---------------");
                                                        writeFile2("---------------");
                                                        System.out.println(sentence);
                                                        writeFile2(sentence);
                                                        System.out.println("---------------");
                                                        writeFile2("---------------");
                                                        quickNerAnnotator(sentence);
                                                        //add nammed entities counter
                                                        sentence="";
						}else{}
					}
				}
				/* Sentence Counter END */

				/* Close the text file and open back up */
				infile.close();
				//file=new File("C:/Users/Imane/Documents/folders/hello.txt");
				//infile = new Scanner(file);
				System.out.println("Sentence Count:"+sentenceCounter);
				// Create output file
				File output2=new File("C:/Users/Imane/Documents/output2.txt");
				while(!output2.exists()){
					output2.createNewFile();
				}
				Writer toOutput = null;
				toOutput = new BufferedWriter(new FileWriter(output2));  
				
				((BufferedWriter) toOutput).newLine();
				toOutput.write("Sentence Count: "+sentenceCounter);
				toOutput.close();
			}
		}
		else
		{
			System.out.println("The file you entered either do not exist or the name is spelled wrong. Goodbye!");
			System.exit(-1);
		}
		}catch (IOException e){}
		catch (NullPointerException e){}
		catch (RuntimeException e) {}

	}
}

