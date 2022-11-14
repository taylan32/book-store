package com.example.bookstore.dto;

public enum CategoryType {
	RESEARCH_HISTORY("Arastirma - Tarih"),
	SCIENCE("Bilim"),
	COMIC("Cizgi - Roman"),
	CHILD_AND_YOUGH("Cocuk ve Genclik"),
	LESSON_TEST_BOOKS("Ders / Sınav Kitaplari"),
	RELIGION("Dini Kitaplar"),
	LITERATURE("Edebiyat"),
	EDUCATION("Egitim"),
	PHILOSOPHY("Felsefe"),
	FOREIGN_LANGUAGES("Yabanci dil"),
	HOBBY("Hobi"),
	ART_DESIGN("Mimari / Dizayn"),
	OTHER("Digerleri");
	
	
	private final String value;
	
	CategoryType(String value) {
		this.value = value;
	}
	public String getValue() {
		return this.value;
	}
}
