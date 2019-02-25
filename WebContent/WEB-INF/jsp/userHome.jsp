<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page import="com.recipe.generator.model.Ingredient"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="bg">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
<style type="text/css">
<%@include file="checkbox-switch.css"%>
.bg {
	/* The image used */
	background-image:
		url("http://evgeni.mk/wp-content/uploads/2015/07/awesome-fruit-and-vegetable-backgrounds.jpg");
	/* Full height */
	height: 100%;
	/* Center and scale the image nicely */
	background-position: center;
	background-repeat: no-repeat;
	background-size: cover;
}
</style>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body class="bg">
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header">
					Welcome, ${name}. <a href="logout.html"><span
						style="float: right;" id="logout" class="glyphicon glyphicon-off"></span></a>
				</h1>
				<ul id="myTab" class="nav nav-tabs">
					<li class="active"><a href="#home" data-toggle="tab"> My
							Fridge </a></li>
					<li><a href="#a" data-toggle="tab">Generate Recipe</a></li>
					<li><a href="#b" data-toggle="tab" id="getFavorites">Favorites</a></li>
				</ul>
				<div id="myTabContent" class="tab-content">
					<div class="tab-pane fade in active" id="home">
						<div class="content_accordion">
							<div class="panel-group" id="accordion">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a data-toggle="collapse" data-parent="#accordion"
												href="#essay">Ingredients in fridge</a>
										</h4>
									</div>
									<div id="essay" class="panel-collapse collapse in">
										<div class="panel-body">
											<div class="ingredients">
												<%
													List<Ingredient> i = (List<Ingredient>) request.getAttribute("ingredients");
													for (int j = 0; j < i.size(); j++) {
												%>
												<a
													class="btn btn-square-toround btn-xl btn-bordered-primary"
													data-toggle="modal" data-target="#floatLabelDemo-UP"
													id="ingredient"><span
													class="glyphicon glyphicon-remove"></span> <%
 	out.print(i.get(j).getIngredientName());
 %> </a>
												<%
													}
												%>
											</div>
										</div>
									</div>
								</div>
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a data-toggle="collapse" data-parent="#accordion"
												href="#eng">Search Ingredients to add to MyFridge</a>
										</h4>
									</div>
									<div id="eng" class="panel-collapse collapse">
										<div class="panel-body">
											<div class="span12">
												<form:form method="GET" action="searchIngredients.html"
													modelAttribute="user" id="custom-search-form"
													class="form-search form-horizontal">
													<div class="input-append span12">
														<input type="text" id="search" name="search"
															class="search-query mac-style" placeholder="Search">
														<button type="submit" id="submit" class="btn">
															<i class="glyphicon glyphicon-search"></i>
														</button>
													</div>
												</form:form>
											</div>
											<br />
											<div class="col-md-4" id="hide" style="display: none;">
												<div class="panel panel-primary">
													<div class="panel-heading">
														<h3 class="panel-title">Search results</h3>
													</div>
													<ul class="list-group">
													</ul>
												</div>
												<button class="btn btn-lg btn-primary btn-block"
													name="Submit" id="addIngredientsButton" type="Submit">Add
													Ingredients</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
						<!--accordion end-->
					</div>
					<div class="tab-pane fade" id="a">
						<div class="content_accordion">
							<div class="panel-group" id="ga">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a data-toggle="collapse" data-parent="#ga" href="#aa">1.
												Choose the ingredient and submit </a>
										</h4>
									</div>
									<div id="aa" class="panel-collapse collapse in">
										<div class="panel-body">
											<div class="panel panel-default">
												<div class="panel-body">
													<%
														List<Ingredient> ings = (List<Ingredient>) request.getAttribute("ingredients");
														for (int j = 0; j < ings.size(); j++) {
													%>
													<div class="form-group">
														<label
															class="checkbox-inline checbox-switch switch-primary">
															<input type="checkbox" name=""
															value=<%=i.get(j).getIngredientName()%> /> <span></span>
															<%
																out.print(i.get(j).getIngredientName());
															%>
														</label>
													</div>

													<%
														}
													%>
													<button type="button" class="btn btn-primary"
														id="generateRecipe">Generate Recipe</button>
												</div>
											</div>
										</div>
										<div class="panel-body" id="recipeSearch"
											style="display: none;">
											
										</div>
									</div>
								</div>
							</div>
						</div>
						<!--accordion end-->
					</div>
					<div class="tab-pane fade" id="b">
						<div class="content_accordion">
							<div class="panel-group" id="gb">
								<div class="panel panel-default">
									<div class="panel-heading">
										<h4 class="panel-title">
											<a data-toggle="collapse" data-parent="#gb" href="#ps">
												Recipes in your favorites list </a>
										</h4>
									</div>
									<div id="ps" class="panel-collapse collapse in">
										<div class="panel-body" id="displayFavorites" style="display:none;">
										</div>
									</div>
								</div>
							</div>
						</div>
						<!--accordion end-->
					</div>
				</div>
			</div>
		</div>
		<!-- /.row -->

	</div>
</body>
<script type="text/javascript">
	$('#submit')
			.click(
					function(e) {
						e.preventDefault(); // <------------------ stop default behaviour of button
						var element = this;
						$
								.ajax({
									url : $('#custom-search-form').attr(
											"action"),
									type : "GET",
									data : "search=" + $('#search').val(),
									traditional : true,
									success : function(response) {
										$(".list-group").empty()
										var results = response.split(",");
										console.log(results);
										for (var i = 0; i < results.length; i++) {
											var temp = "<div class='form-check'> <input class='form-check-input' type='checkbox' value='"+results[i]+"'> <label class='form-check-label' for='defaultCheck1'>"
													+ results[i]
													+ "</label></div>"
											//var temp = "<input class='form-check-input' type='checkbox' value='"+results[i]+"' id='defaultCheck1'>"
											//var temp = "<a href='#' class='list-group-item'>"+results[i]+"</a>";
											$(".list-group").append(temp);
										}

										$("#hide").show();

									},
									error : function(xhr, status, error) {
										alert(xhr.responseText);
									}
								});
					});

	$('#addIngredientsButton').click(function(e) {
		var ingredients = [];
		$('.list-group input:checked').each(function() {
			//console.log($(this).attr('value'));
			ingredients.push($(this).attr('value'))
		});
		e.preventDefault(); // <------------------ stop default behaviour of button
		var element = this;
		$.ajax({
			url : "addIngredients.html",
			type : "GET",
			data : "ingredients=" + ingredients,
			traditional : true,
			success : function(response) {
				console.log(response);
				alert(response);
				location.reload();
			},
			error : function(xhr, status, error) {
				alert(xhr.responseText);
			}
		});
	});

	$(".ingredients a").click(function(e) {
		e.preventDefault();
		$.ajax({
			url : "removeIngredient.html",
			type : "GET",
			data : "ingredient=" + $(this).text(),
			traditional : true,
			success : function(response) {
				alert(response);
				location.reload();
			},
			error : function(xhr, status, error) {
				alert(xhr.responseText);
			}
		});
	});

	$("#logout").click(function(e) {
		alert("You are logging out.");
	});

	$("#generateRecipe")
			.click(
					function(e) {
						var ingredients = [];
						$('.form-group input:checked').each(function() {
							ingredients.push($(this).attr('value'));
						});
						e.preventDefault();
						$
								.ajax({
									url : "generateRecipe.html",
									type : "GET",
									data : "ingredients=" + ingredients,
									traditional : true,
									success : function(response) {
										//console.log(response);
										var tempObj = JSON.parse(response);
										var myObj = tempObj;
										console.log(tempObj);
										$('#recipeSearch').empty();
												for (i in myObj) {
											var tempHtml = "<article class='search-result row'><div class='col-xs-12 col-sm-12 col-md-3'><a href='"+myObj[i].recipeUrl+"' title='"+myObj[i].title+"' target='_blank' class='thumbnail'><img src='"+myObj[i].imageUrl+"' alt='Lorem ipsum' /></a></div><div class='col-xs-12 col-sm-12 col-md-2'><ul class='meta-search'><li><i class='glyphicon glyphicon-thumbs-up'></i> <span>Likes: "+myObj[i].likes+"</span></li><li><i class='glyphicon glyphicon-warning-sign'></i> <span>Missing ingredient count: "+myObj[i].missedIngredientCount+"</span></li></ul></div><div id='favor' class='col-xs-12 col-sm-12 col-md-7 excerpet'><h3><a href='"+myObj[i].recipeUrl+"' title='' target='_blank'>"+myObj[i].title+"</a></h3><p>"+myObj[i].summary+"</p><button id='favorite' value='"+myObj[i].id+"' type='button' class='btn btn-info'>Add/Remove Favorite</button></div><span class='clearfix borda'></span></article><hr>"
											//console.log(myObj[i].recipeUrl+"----"+myObj[i].imageUrl);
											$('#recipeSearch').append(tempHtml);
										}
										$('#recipeSearch').show();
									},
									error : function(xhr, status, error) {
										alert(xhr.responseText);
									}
								});
					});
		
		$("#recipeSearch").on('click', '#favorite', function(e){
		e.preventDefault();
		$.ajax({
			url : "addOrRemoveFavourite.html",
			type : "GET",
			data : "recipeId=" + $(this).attr('value'),
			traditional : true,
			success : function(response) {
				if(response=="favorite"){
					alert('added to favorites');
				}else{
					alert('removed from favorites');
				}
			},
			error : function(xhr, status, error) {
				alert(xhr.responseText);
			}
		});
	});
		
		$("#displayFavorites").on('click', '#favorite', function(e){
			e.preventDefault();
			$.ajax({
				url : "addOrRemoveFavourite.html",
				type : "GET",
				data : "recipeId=" + $(this).attr('value'),
				traditional : true,
				success : function(response) {
					if(response=="favorite"){
						alert('added to favorites');location.reload();
					}else{
						alert('removed from favorites');location.reload();
					}
				},
				error : function(xhr, status, error) {
					alert(xhr.responseText);location.reload();
				}
			});
		});
	
		$("#getFavorites").click(function(e) {
			console.log("Clicked favorites...");
			$.ajax({
				url : "getFavorites.html",
				type : "GET",
				traditional : true,
				success : function(response) {
					var tempObj = JSON.parse(response);
					var myObj = tempObj;
					console.log(tempObj);
					$('#displayFavorites').empty();
							for (i in myObj) {
						var tempHtml = "<article class='search-result row'><div class='col-xs-12 col-sm-12 col-md-3'><a href='"+myObj[i].recipeUrl+"' title='"+myObj[i].title+"' target='_blank' class='thumbnail'><img src='"+myObj[i].imageUrl+"' alt='Lorem ipsum' /></a></div><div class='col-xs-12 col-sm-12 col-md-2'></div><div id='favor' class='col-xs-12 col-sm-12 col-md-7 excerpet'><h3><a href='"+myObj[i].recipeUrl+"' title='' target='_blank'>"+myObj[i].title+"</a></h3><p>"+myObj[i].summary+"</p><button id='favorite' value='"+myObj[i].id+"' type='button' class='btn btn-info'>Remove from Favorites</button></div><span class='clearfix borda'></span></article><hr>"
						//console.log(myObj[i].recipeUrl+"----"+myObj[i].imageUrl);
						$('#displayFavorites').append(tempHtml);
					}
					$('#displayFavorites').show();
				},
				error : function(xhr, status, error) {
					alert(xhr.responseText);
				}
			});
		});
	
</script>
</html>