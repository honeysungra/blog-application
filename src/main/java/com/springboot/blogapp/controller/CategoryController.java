package com.springboot.blogapp.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blogapp.payload.CategoryDto;
import com.springboot.blogapp.service.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/categories")
@Tag(name = "CRUD API for Category Resource")
public class CategoryController {
	private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @Operation(
			summary = "Create Category REST API",
			description = "Create Category REST API is used to save category into database"
			)
	@ApiResponse(
			responseCode = "201",
			description = "Http Status 201 Created"
			)
	
	@SecurityRequirement(
			name = "Bear Authentication"
			)
	//add category
	@PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto){
        CategoryDto savedCategory = categoryService.addCategory(categoryDto);
        return new ResponseEntity<>(savedCategory, HttpStatus.CREATED);
    }
    @Operation(
			summary = "Get Category By ID REST API",
			description = "Get Category By Id REST API is used to get single category from database"
			)
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 200 Success"
			)
	//get Category By id
	@GetMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> getCategory(@PathVariable Long categoryId){
		return ResponseEntity.ok(categoryService.getCategory(categoryId));
	}
	@Operation(
			summary = "Get All Category REST API",
			description = "Get All Category REST API is used to get all category from database"
			)
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 200 Success"
			)
	//get All the Categories
	@GetMapping
	public ResponseEntity<List<CategoryDto>> getAllCategories(){
		return ResponseEntity.ok(categoryService.getAllCategories());
	}  
	
	@Operation(
			summary = "Update Category By ID REST API",
			description = "Update Category By Id REST API is used to update category in the database"
			)
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 200 Success"
			)
	
	@SecurityRequirement(
			name = "Bear Authentication"
		)
	//update category by id
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/{categoryId}")
	public ResponseEntity<CategoryDto> updateCategory(@RequestBody CategoryDto categoryDto, @PathVariable Long categoryId){
		return ResponseEntity.ok(categoryService.updateCategory(categoryDto, categoryId));
	}
	
	@Operation(
			summary = "Delete Category  By ID REST API",
			description = "Delete Category  By Id REST API is used to delete category  from the database"
			)
	@ApiResponse(
			responseCode = "200",
			description = "Http Status 200 Success"
			)
	@SecurityRequirement(
			name = "Bear Authentication"
		)
	//delete category by id
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/{categoryId}")
	public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId){
		categoryService.deleteCategory(categoryId);
		return ResponseEntity.ok("Category deleted Successfully!!");
	}
}
