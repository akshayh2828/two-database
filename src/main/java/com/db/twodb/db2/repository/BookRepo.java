package com.db.twodb.db2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.db.twodb.db2.entity.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer>{
@Query("SELECT b FROM Book b where b.name= :name")
	public Book getBookByName(@Param("name") String name);
}
