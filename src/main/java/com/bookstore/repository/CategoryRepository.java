package com.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bookstore.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>
{
	public List<Category> findAllByOrderByNameAsc();
}
