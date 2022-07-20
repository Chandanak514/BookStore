package com.bookstore.controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookstore.entity.Book;
import com.bookstore.entity.Category;
import com.bookstore.entity.Issue;
import com.bookstore.entity.IssuedBook;
import com.bookstore.entity.Member;
import com.bookstore.service.BookService;
import com.bookstore.service.CategoryService;
import com.bookstore.service.IssueService;
import com.bookstore.service.IssuedBookService;
import com.bookstore.service.MemberService;

@RestController
@RequestMapping(value = "/book")
public class HomeController
{

	@Autowired
	private BookService bookService;
	
	@Autowired
	private CategoryService categoryService;
	@Autowired 
	private MemberService memberService;
	@Autowired
	private IssueService issueService;
	@Autowired
	private IssuedBookService issuedBookService;
	@GetMapping("/categories")
	public List<Category> categories() 
	{
		return categoryService.getAllBySort();
	}
	
	@GetMapping("/listBook")
	public List<Book> showBooksPage() 
	{
		return bookService.getAll();
	}

	@PostMapping("/addBook")
	public Book addBookPage(@RequestBody Book book) 
	{
		
		return bookService.addNew(book);
	}
	
	@PutMapping("/editBook/{id}")
	public Book editBookPage(@PathVariable(name = "id") Long id,  @RequestBody Book book)
	{
		Book bookData = bookService.get(id);
		if( bookData != null ) 
		{
			bookData.setAuthors(book.getAuthors());
			bookData.setCategory(null);
			bookData.setIsbn(book.getIsbn());
			bookData.setPublisher(book.getPublisher());
			bookData.setTag(book.getTag());
			bookData.setTitle(book.getTitle());
			
			return bookService.save(bookData);
		}
		else 
		{
			return null;
		}
		
	}
	@DeleteMapping("/deleteBook/{id}")
	public void delete(@PathVariable("id") Book book){
	 bookService.delete(book);
	}
	@PostMapping("/addmember")
	public Member addMember(@RequestBody Member member) 
	{
		
		return memberService.addNew(member);
	}
	
	
	@DeleteMapping("/deleteMember/{id}")
	public void delete(@PathVariable("id") Long id){
	 memberService.delete(id);
	}
	@PutMapping("/editmember/{id}")
	public Member editMember(@PathVariable(name = "id") Long id,  @RequestBody Member member)
	{
		Member memberData = memberService.get(id);
		if( memberData != null ) 
		{
			memberData.setId(id);
			memberData.setFirstName(member.getFirstName());
			memberData.setMiddleName(member.getMiddleName());
			memberData.setLastName(member.getLastName());
			memberData.setGender(member.getGender());
			memberData.setDateOfBirth(member.getDateOfBirth());
			memberData.setJoiningDate(member.getJoiningDate());
			memberData.setContact(member.getContact());
			memberData.setEmail(member.getEmail());
			
			return memberService.save(memberData);
		}
		else 
		{
			return null;
		}
		
	}
	@GetMapping("/listmember")
	public List<Member> showMembers() 
	{
		return memberService.getAll();
	}
	@GetMapping("/listcategory")
	public List<Category> showCategories() 
	{
		return categoryService.getAll();
	}
	@PutMapping("/editcategory/{id}")
	public Category editCategory(@PathVariable(name = "id") Long id,  @RequestBody Category category)
	{
		Category categoryData = categoryService.get(id);
		if( categoryData != null ) 
		{
			categoryData.setId(id);
			categoryData.setName(category.getName());
			categoryData.setShortName(category.getShortName());
			categoryData.setNotes(category.getNotes());
			
		
			return categoryService.save(categoryData);
		}
		else 
		{
			return null;
		}
		
	}
	@DeleteMapping("/deletecategory/{id}")
	public void deletecategory(@PathVariable("id") Long id){
	 categoryService.delete(id);
	}
	@PostMapping("/addCategory")
	public Category addCategory(@RequestBody Category category) 
	{
		
		return categoryService.addNew(category);
	}
	@GetMapping("/listissue")
	public List<Issue> showIssue() 
	{
		return issueService.getAll();
	}
	@GetMapping("/listissue/{id}")
	public List<Issue> showIssueId() 
	{
		return issueService.getAll();
	}

	@PostMapping("/addissue")
	public Issue addIssue(@RequestBody Issue issue) 
	{
		
		return issueService.addNew(issue);
	}
	@GetMapping("/listissuedbook/{id}")
	public List<IssuedBook> showIssueBookId() 
	{
		
		return issuedBookService.getAll();
	}
	
	
	
	
	
	
	
	

	/**
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveBook(@Valid Book book, BindingResult bindingResult, final RedirectAttributes redirectAttributes)
	{
		if( bindingResult.hasErrors() ) 
		{
			return "/book/form";
		}
		
		if( book.getId() == null )
		{
			if( bookService.getByTag(book.getTag()) != null )
			{
				bindingResult.rejectValue("tag", "tag", "Tag already exists");
				return "/book/form";
			} 
			else 
			{
				bookService.addNew(book);
				redirectAttributes.addFlashAttribute("successMsg", "'" + book.getTitle() + "' is added as a new Book.");
				return "redirect:/book/add";
			}
		} 
		else 
		{
			Book updatedBook = bookService.save(book);
			redirectAttributes.addFlashAttribute("successMsg", "Changes for '" + book.getTitle() + "' are saved successfully. ");
			return "redirect:/book/edit/"+updatedBook.getId();
		}
	}
	
	@RequestMapping(value = "/remove/{id}", method = RequestMethod.GET)
	public String removeBook(@PathVariable(name = "id") Long id, Model model)
	{
		Book book = bookService.get( id );
		if( book != null )
		{
			if( bookService.hasUsage(book) )
			{
				model.addAttribute("bookInUse", true);
				return showBooksPage(model);
			} 
			else 
			{
				bookService.delete(id);
			}
		}
		return "redirect:/book/list";
	}**/
}
