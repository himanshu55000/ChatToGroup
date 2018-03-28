/**
 * 
 */
app.factory('BlogService',function($http){
	var blogService={};
		
	blogService.addBlog=function(blog){
		return $http.post(BASE_URL+"/addBlog",blog)
	}
	
	blogService.deleteBlog=function(id){
		return $http['delete'](BASE_URL+"/deleteBlog/"+id)
	}
	
	blogService.getAllBlog=function(approved){
		return $http.get(BASE_URL+"/getAllBlog/"+approved)
	}
	
	blogService.getAllBlogByUser=function(id){
		return $http.get(BASE_URL+"/getAllBlogByUser/"+id)
	}
	
	blogService.getBlogById=function(id){
		return $http.get(BASE_URL+"/getBlogById/"+id)
	}
	
	blogService.approveBlog=function(id){
		return $http.get(BASE_URL+"/approveBlog/"+id)
	}
	
	blogService.rejectBlog=function(id){
		return $http.get(BASE_URL+"/rejectBlog/"+id)
	}
	
	blogService.getAllBlogComment=function(id){
		return $http.get(BASE_URL+"/getAllBlogComment/"+id)
	}
	
	blogService.addBlogComment=function(blogComment){
		return $http.post(BASE_URL+"/addBlogComment",blogComment)
	}
	
	return blogService;
})
