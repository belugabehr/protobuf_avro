package com.szymon_kaluza.protobuf_avro.benchmark;

import com.szymon_kaluza.protobuf_avro.proto.model.Author;
import com.szymon_kaluza.protobuf_avro.proto.model.Book;
import com.szymon_kaluza.protobuf_avro.proto.model.Library;
import org.apache.commons.text.RandomStringGenerator;
import org.apache.commons.text.TextRandomProvider;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ProtoBenchmarkDataFactory {

    private static final Random RANDOM = new Random(BenchmarkRunner.RANDOM_SEED);
    private static final RandomStringGenerator STRING_GENERATOR = new RandomStringGenerator.Builder()
            .usingRandom(new TextRandomProvider() {
                @Override
                public int nextInt(int i) {
                    return RANDOM.nextInt();
                }
            }).build();

    public static Library getLibrary(List<Book> books) {
        return Library.newBuilder()
                .setAddress(STRING_GENERATOR.generate(3, 16))
                .addAllBooks(books)
                .build();
    }

    public static List<Book> getManyBooks(int numberOfBooks) {
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < numberOfBooks; i++) {
            books.add(getBook());
        }
        return books;
    }

    public static Book getBook() {
        return Book.newBuilder()
                .setTitle(STRING_GENERATOR.generate(3, 16))
                .setAuthor(getAuthor())
                .setPages(RANDOM.nextLong(13L))
                .setAvailable(RANDOM.nextBoolean())
                .build();
    }

    public static Author getAuthor() {
        return Author.newBuilder()
                .setName(STRING_GENERATOR.generate(3, 16))
                .setSurname(STRING_GENERATOR.generate(3, 16))
                .setNationality(STRING_GENERATOR.generate(3, 16))
                .build();
    }

    public static Library getFixedLibrary(List<Book> books) {
        return Library.newBuilder()
                .setAddress("address")
                .addAllBooks(books)
                .build();
    }

    public static List<Book> getManyFixedBooks(int numberOfBooks) {
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < numberOfBooks; i++) {
            books.add(getFixedBook(i));
        }
        return books;
    }

    public static Book getFixedBook(int index) {
        return Book.newBuilder()
                .setTitle("title" + index)
                .setAuthor(getFixedAuthor(index))
                .setPages(index)
                .setAvailable(true)
                .build();
    }

    public static Author getFixedAuthor(int index) {
        return Author.newBuilder()
                .setName("name" + index)
                .setSurname("surname" + index)
                .setNationality("nationality" + index)
                .build();
    }
}
