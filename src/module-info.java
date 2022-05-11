
	import java.io.File;
	import java.io.IOException;
	import java.nio.charset.StandardCharsets;
	import java.nio.file.FileAlreadyExistsException;
	import java.nio.file.Files;
	import java.nio.file.Path;
	import java.nio.file.Paths;
	import java.util.ArrayList;
	import java.util.List;
	import java.util.stream.Collectors;
	import java.util.stream.Stream;

	public class Main {

		// ######################## MODIFY AND SAVE Template#########################
		private static void writetofile(String name) {
			System.out.println(name);
			String one = new String("first string");
			String two = new String("second string");
			String towrite = new String(one + " " + name + " " + two);

			try {
				Path newFile = Paths.get(name + ".ps1");
				Files.createFile(newFile);
				Files.write(newFile, "what".getBytes(StandardCharsets.UTF_8));
			} catch (FileAlreadyExistsException e) {
				// e.printStackTrace();

				System.out.println("File Already exists!! " + name);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// ######################### HARD DRIVE SEARCH ###################################
		public static void main(String[] args) {
			String directory = "C:/test/";

			// Recursively list all files
			List<Path> pathList = new ArrayList<>();

			try (Stream<Path> stream = Files.walk(Paths.get(directory))) {
				// Do something with the stream.
				pathList = stream.map(Path::normalize).filter(Files::isRegularFile)
						.filter(path -> path.getFileName().toString().endsWith(".exe")).collect(Collectors.toList());
			} catch (IOException e) {
				e.printStackTrace();
			}

			// ################ COPY AND PASTE Version 2 ###################################################
			// passes the pathList to writeToFile method in the form of the last file in the path
			// i.e. from
			// "C:\RandomFolder\GoogleUpdateSetup.exe"
			// to "GoogleUpdateSetup.exe"
			// and converts from Type <Path> to type <String>
			pathList.forEach((n) -> writetofile(n.getFileName().toString()));

			
			
			
			// ******************Currently not in use*****************************************
			// ################## COPY AND PASTE Version 1 ###################################
			// Another Slower Way to get the File names and store them into array
			// Create array to store the names of the files without path names
			String[] FileNameArr = new String[pathList.size()];

			// loop through the list
			for (int i = 0; i < pathList.size(); i++) 
			{
				File f = new File(pathList.get(i).toString());
				System.out.println(f.getName());
				FileNameArr[i] = f.getName();
			}

		}

	}
}