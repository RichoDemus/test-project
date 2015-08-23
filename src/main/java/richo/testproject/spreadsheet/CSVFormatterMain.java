package richo.testproject.spreadsheet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

class CSVFormatterMain
{
	private final Logger logger = LoggerFactory.getLogger(getClass());

	public static void main(String[] args)
	{
		new CSVFormatterMain().start();
	}

	private void start()
	{
		final List<Reference> references = parse();
		System.out.println("BEGIN\n\n");
		references.stream()
				.map(reference ->
				{
					return String.format("%s (%s) %s %s\n",
							reference.authors, reference.year, reference.title, reference.misc);
				})
				.forEach(System.out::println);

		final Consumer<String> consumer = a -> {
			if (a.contains("¼") ||
					a.contains("Ã©") ||
					a.contains("Ã„") ||
					a.contains("Ã¨") ||
					a.contains("Ã¤") ||
					a.contains("Î²") ||
					a.contains("Ã¶"))
			{
				throw new RuntimeException(a);
			}
		};
		references.stream().map(r -> r.authors).forEach(consumer);
		references.stream().map(r -> r.title).forEach(consumer);
		references.stream().map(r -> r.misc).forEach(consumer);
	}

	public List<Reference> parse()
	{
		List<Reference> result = new ArrayList<>();
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = "\t";

		try
		{

			//br = new BufferedReader(new FileReader(csvFile));
			br = new BufferedReader(
					new InputStreamReader(getClass().getClassLoader().getResourceAsStream("excel/ih referenser - sorted.tsv")));
			while ((line = br.readLine()) != null)
			{
				//System.out.println(line);


				String[] ref = line.split(cvsSplitBy);

				result.add(new Reference(ref[0], ref[1], ref[2], ref[3]));

			}

		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (br != null)
			{
				try
				{
					br.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		result.remove(0);
		return result;
	}
}
