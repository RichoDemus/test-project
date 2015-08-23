package richo.testproject.spreadsheet;

import java.util.Objects;

class Reference
{
	final String authors;
	final String year;
	final String title;
	final String misc;

	Reference(String authors, String year, String title, String misc)
	{
		this.authors = authors
				.replaceAll("MÃ¼ller FJ", "Müller FJ")
				.replaceAll("CarÃ©n H", "Carén H")
				.replaceAll("BÃ©nard", "Bénard")
				.replaceAll("RaguÃ©nez", "Raguénez")
				.replaceAll("FrÃ©bourg", "Frébourg")
				.replaceAll("CoppÃ©e", "Coppée")
				.replaceAll("Ã„hrlund", "Ährlund")
				.replaceAll("LilljebjÃ¶rn", "Lilljebjörn")
				.replaceAll("BjÃ¶rk", "Björk")
				.replaceAll("MossÃ©", "Mossé")
				.replaceAll("Ã˜ra I", "Øra I")
				.replaceAll("BrugiÃ¨res", "Brugières")
				.replaceAll("SveinbjÃ¶rnsson", "Sveinbjörnsson")
				.replaceAll("FrÃ¶hling", "Fröhling")
				.replaceAll("DÃ\u00ADaz de StÃ¥hl T", "Díaz de Ståhl T")
				.replaceAll("TrÃ¤ger", "Träger")
				.replaceAll("AndÃ¤ng", "Andäng")
				.replaceAll("BÃ¶rjesson", "Börjesson")
				.replaceAll("SjÃ¶berg", "Sjöberg");
		this.year = year;
		this.title = title
				.replaceAll("Î²", "β");
		this.misc = misc;
	}

	@Override
	public String toString()
	{
		return authors;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;
		}
		if (o == null || getClass() != o.getClass())
		{
			return false;
		}
		Reference reference = (Reference) o;
		return Objects.equals(authors, reference.authors) &&
				Objects.equals(year, reference.year) &&
				Objects.equals(title, reference.title) &&
				Objects.equals(misc, reference.misc);
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(authors, year, title, misc);
	}
}
