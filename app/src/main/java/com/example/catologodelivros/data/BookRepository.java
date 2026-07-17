package com.example.catologodelivros.data;

import com.example.catologodelivros.R;
import com.example.catologodelivros.model.Book;
import com.example.catologodelivros.util.SearchUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class BookRepository {

    private static final List<Book> BOOKS = createBooks();

    private BookRepository() {
    }

    public static List<Book> getAllBooks() {
        return new ArrayList<>(BOOKS);
    }

    public static Book getBookById(int id) {
        for (Book book : BOOKS) {
            if (book.getId() == id) {
                return book;
            }
        }
        return null;
    }

    public static List<Book> searchBooks(String query) {
        if (query == null || query.trim().isEmpty()) {
            return getAllBooks();
        }
        List<Book> results = new ArrayList<>();
        for (Book book : BOOKS) {
            if (SearchUtils.matches(book, query)) {
                results.add(book);
            }
        }
        return results;
    }

    private static List<Book> createBooks() {
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "Android Essencial", "Marina Costa", "Tecnologia", "desenvolvimento mobile android",
                "Guia introdutório sobre criação de aplicativos Android com foco em boas práticas.", 49.90, 8, R.drawable.cover_tecnologia));
        books.add(new Book(2, "Java na Prática", "Carlos Nogueira", "Programação", "java orientação a objetos",
                "Exemplos objetivos para aplicar orientação a objetos e coleções em Java.", 59.90, 6, R.drawable.cover_programacao));
        books.add(new Book(3, "Kotlin para Projetos", "Helena Prado", "Programação", "kotlin produtividade android",
                "Livro voltado à produtividade com Kotlin em projetos modernos.", 64.90, 3, R.drawable.cover_programacao_alt));
        books.add(new Book(4, "Clássicos Brasileiros", "Ana Teles", "Literatura", "romances contos literatura brasileira",
                "Seleção comentada de obras fundamentais da literatura nacional.", 39.90, 5, R.drawable.cover_literatura));
        books.add(new Book(5, "Horizonte de Marte", "Rafael Lima", "Ficção", "ficcao cientifica aventura espacial",
                "Aventura espacial com foco em exploração, ciência e sobrevivência.", 44.50, 0, R.drawable.cover_ficcao));
        books.add(new Book(6, "Linha do Tempo do Brasil", "Eduardo Vale", "História", "historia brasil fatos marcantes",
                "Resumo organizado de eventos que marcaram a formação do Brasil.", 52.00, 4, R.drawable.cover_historia));
        books.add(new Book(7, "Didática em Sala", "Paula Mendes", "Educação", "metodologias ensino aprendizagem",
                "Estratégias didáticas para tornar o processo de ensino mais participativo.", 41.75, 7, R.drawable.cover_educacao));
        books.add(new Book(8, "Gestão sem Mistério", "Ricardo Farias", "Administração", "lideranca processos negocios",
                "Conceitos de gestão, liderança e organização aplicados ao dia a dia.", 55.40, 2, R.drawable.cover_administracao));
        books.add(new Book(9, "Ciência do Cotidiano", "Bianca Rocha", "Ciência", "curiosidades metodo cientifico",
                "Explica fenômenos do cotidiano com linguagem simples e científica.", 47.30, 9, R.drawable.cover_ciencia));
        books.add(new Book(10, "Dados e Algoritmos", "Lucas Vieira", "Tecnologia", "estruturas de dados algoritmos",
                "Visão prática sobre algoritmos, listas, filas e desempenho.", 69.90, 1, R.drawable.cover_tecnologia_alt));
        books.add(new Book(11, "Empatia em Redes", "Juliana Serra", "Comunicação", "comunicacao digital atendimento",
                "Aborda relacionamento com clientes e comunicação em canais digitais.", 36.90, 10, R.drawable.cover_comunicacao));
        books.add(new Book(12, "Oficina de Leitura", "Sérgio Matos", "Educação", "leitura interpretação formação de leitores",
                "Atividades e propostas para estimular leitura e interpretação textual.", 33.80, 0, R.drawable.cover_educacao_alt));
        return Collections.unmodifiableList(books);
    }
}
