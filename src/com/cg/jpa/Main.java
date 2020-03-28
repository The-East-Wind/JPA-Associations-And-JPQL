package com.cg.jpa;

import com.cg.jpa.entity.Author;
import com.cg.jpa.entity.Book;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    static void lab21(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAUnit");
        EntityManager em = emf.createEntityManager();
        Author author = new Author();
        author.setAuthorName("Stephen King");
        Book book1 = new Book();
        book1.setIsbn("9789851807822");
        book1.setPrice(250.0f);
        book1.setTitle("It");
        Set<Book> books = new HashSet<>();
        books.add(book1);
        author.setBooks(books);
        em.getTransaction().begin();
        em.persist(author);
        em.persist(book1);
        em.getTransaction().commit();
        System.out.println("Data Inserted Successfully!");
        int authorId = author.getAuthorId();
        em.close();
        emf.close();
        /*emf = Persistence.createEntityManagerFactory("JPAUnit");
        em = emf.createEntityManager();
        Author authorFromDB = em.find(Author.class,authorId);
        System.out.println(authorFromDB.getAuthorId());
        System.out.println(authorFromDB.getAuthorName());
        System.out.println(authorFromDB.getBooks());
        em.close();
        emf.close();*/
    }
    // query all books
    static List<Book> getAllBooks(){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAUnit");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("FROM Book");
        List<Book> books = query.getResultList();
        em.close();
        emf.close();
        return books;
    }
    // query all books written by an author
    static List<Book> getAllBooksByAuthor(String authorName){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAUnit");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT b FROM Book b,Author a WHERE a.authorName=:givenAuthorName");
        query.setParameter("givenAuthorName",authorName);
        List<Book> books = query.getResultList();
        em.close();
        emf.close();
        return books;
    }
    static List<Book> getBooksInARange(float lower,float upper){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAUnit");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("FROM Book b WHERE b.price BETWEEN :lower AND :upper");
        query.setParameter("lower",lower);
        query.setParameter("upper",upper);
        List<Book> books = query.getResultList();
        em.close();
        emf.close();
        return books;
    }
    static Author getAuthor(String isbn){
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPAUnit");
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("SELECT a FROM Author a,Book b WHERE b.isbn=:isbn");
        query.setParameter("isbn",isbn);
        List<Author> authors = query.getResultList();
        em.close();
        emf.close();
        if(authors.size()!=0)
            return authors.get(0);
        else
            return null;
    }
    public static void main(String[] args) {
        //lab21();
        // 2.2 i)
        System.out.println("All Books in Database:");
        System.out.println(getAllBooks());
        //2.2 ii)
        System.out.println("All books written by a given Author");
        System.out.println(getAllBooksByAuthor("Stephen King"));
        //2.2 iii)
        System.out.println("All Books in a given price range");
        System.out.println(getBooksInARange(100.0f,300.0f));
        //2.2 iv)
        System.out.println("Author of a given Book id");
        System.out.println(getAuthor("9789851807822"));
    }
}
