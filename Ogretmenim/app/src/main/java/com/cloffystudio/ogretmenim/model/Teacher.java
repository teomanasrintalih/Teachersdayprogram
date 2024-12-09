package com.cloffystudio.ogretmenim.model;

// Öğretmen bilgilerini temsil eden bir model sınıfı
public class Teacher {

    // Öğretmen bilgileri için gerekli değişkenler
    private int id;         // Öğretmenin benzersiz kimliği
    private String name;    // Öğretmenin adı ve soyadı
    private String branch;  // Öğretmenin branşı (örneğin: Matematik, Fizik)

    // Constructor: Öğretmen nesnesi oluşturmak için kullanılan metot
    public Teacher(int id, String name, String branch) {
        this.id = id;           // Kimlik ataması
        this.name = name;       // İsim ataması
        this.branch = branch;   // Branş ataması
    }

    // Öğretmenin adını almak için kullanılan getter metot
    public String getName() {
        return this.name;       // Adı döndürür
    }

    // Öğretmenin branşını almak için kullanılan getter metot
    public String getBranch() {
        return this.branch;     // Branşı döndürür
    }
}