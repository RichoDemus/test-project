package richo.testproject.spreadsheet;


import org.junit.Ignore;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CSVFormatterMainTest
{
	@Ignore
	@Test
	public void testParse() throws Exception
	{
		final List<Reference> references = new CSVFormatterMain().parse();


		final Reference firstRow = new Reference(
				"Abelson S, Shamai Y, Berger L, Skorecki K, Tzukerman M",
				"2013",
				"Niche-dependent gene expression profile of intratumoural heterogeneous ovarian cancer stem cell populations.",
				"PLOS ONE 8(12):e83651"
		);
		final Reference secondRow = new Reference(
				"Adewumi, O, Aflatoonian, B, Ahrlund-Richter, L, Amit, M  Andrews, P.W., Beighton, G., Bello, P.A., Benvenisty, N, Berry, L.S., Bevan, S",
				"2007",
				"Characterization of human embryonic stem cell lines by the International Stem Cell Initiative.",
				"Nat. Biotechnol 25, 803-816"
		);
		assertEquals(firstRow.authors, references.get(0).authors);
		assertEquals(firstRow.year, references.get(0).year);
		assertEquals(firstRow.title, references.get(0).title);
		assertEquals(firstRow.misc, references.get(0).misc);
		assertEquals(secondRow.authors, references.get(1).authors);
		assertEquals(secondRow.year, references.get(1).year);
		assertEquals(secondRow.title, references.get(1).title);
		assertEquals(secondRow.misc, references.get(1).misc);

	}
}