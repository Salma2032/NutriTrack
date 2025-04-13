<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="ma.ac.esi.nutritrack.model.Meal" %>
<%@ page import="ma.ac.esi.nutritrack.model.Ingredient" %>

    <%
    List<Meal> meals = (List<Meal>) request.getAttribute("meals");
    if (meals == null) {
        meals = new java.util.ArrayList<>();
    }
%>



<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Meal Plan - Gain Weight</title>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
  <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css" rel="stylesheet">
  <style>
    body { background-color: #f8f9fa; }
    .sidebar { height: 100vh; background-color: #2c2c54; color: white; padding-top: 20px; }
    .sidebar a { color: white; display: block; padding: 15px; text-decoration: none; text-align: center; }
    .sidebar a:hover { background-color: #57577d; }
    .meal-card { border-radius: 15px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }
    .kcal-box { background-color: #fff; padding: 20px; border-radius: 15px; box-shadow: 0 2px 5px rgba(0,0,0,0.1); }
    .meal-item { border-radius: 10px; background-color: #fff; padding: 10px; margin-bottom: 10px; text-align: center; box-shadow: 0 1px 3px rgba(0,0,0,0.1); }
    .meal-item img { width: 40px; height: 40px; display: block; margin: 0 auto 5px; }
  </style>
</head>
<body>

<form action="logout" method="post">
    <button type="submit" class="btn btn-danger">Déconnexion</button>
</form>







<div class="container-fluid">
    <div class="row">
      <!-- Sidebar -->
      <div class="col-md-2 sidebar d-flex flex-column align-items-center">
        <a href="#"><i class="bi bi-grid"></i></a>
        <a href="#"><i class="bi bi-search"></i></a>
        <a href="#"><i class="bi bi-people"></i></a>
        <a href="#"><i class="bi bi-star"></i></a>
        <a href="#"><i class="bi bi-calendar"></i></a>
        <a href="#"><i class="bi bi-check-square"></i></a>
        <a href="#"><i class="bi bi-chat"></i></a>
        <a href="#"><i class="bi bi-envelope"></i></a>
      </div>
      
      <!-- Main Content -->
      <div class="col-md-10 p-4">
        <h2><strong>Meal plans</strong> / Gain weight</h2>
        <div class="row my-4">
          <div class="col-md-6">
            <img src="https://via.placeholder.com/600x300" class="img-fluid rounded" alt="Meal Image">
          </div>
          <div class="col-md-6 kcal-box">
            <h3><strong>823 kcal</strong></h3>
            <div class="progress my-3">
              <div class="progress-bar" role="progressbar" style="width: 60%" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100"></div>
            </div>
            <p><i class="bi bi-fire"></i> 283 kcal burned</p>
          </div>
        </div>

        <!-- Meal Sections -->
        <div class="row">
          <% if (meals != null) { 
               for (Meal meal : meals) { %>
                  <div class="col-md-3">
                      <h5><%= meal.getName() %></h5>
                      <% for (Ingredient ing : meal.getIngredients()) { %>
                          <div class="meal-item">
                              <img src="img/<%= ing.getName().replaceAll(" ", "") %>.jpg" alt="<%= ing.getName() %>">
                              <%= ing.getName() %><br>
                              <small><%= ing.getCalories() %> kcal</small>
                          </div>
                      <% } %>
                  </div>
          <% } 
          } else { %>
              <p>Aucun repas disponible.</p>
          <% } %>
        </div>



<!-- Bouton Ajouter un ingrédient-->
<button class="btn btn-primary mb-3" data-bs-toggle="modal" data-bs-target="#addIngredientModal">
 <i class="bi bi-plus-lg"></i> Ajouter un ingrédient
</button>

<!-- Bouton SUPPRIMER un ingrédient-->
<button class="btn btn-primary mb-3" data-bs-toggle="modal" data-bs-target="#deleteIngredientModal">
 <i class="bi bi-plus-lg"></i> Supprimer un ingrédient
</button>

<!-- Bouton modifier un ingrédient-->
<button class="btn btn-primary mb-3" data-bs-toggle="modal" data-bs-target="#editIngredientModal">
 <i class="bi bi-plus-lg"></i> Modifier un ingrédient
</button>

<form action="TotalCaloriesController" method="get">
    <input type="hidden" name="mealId" value="1" />
    <button type="submit">Afficher les calories</button>
</form>

<% for (Meal meal : meals) { %>

    <tr>
        <td><%= meal.getName() %></td>
        <td>Total Calories: <%= meal.getTotalCalories() %></td>
    </tr>
<% } %>



<!-- ajouter modale -->
<div class="modal fade" id="addIngredientModal" tabindex="-1" aria-labelledby="addIngredientModalLabel" aria-hidden="true">
 <div class="modal-dialog">
 <div class="modal-content">
 <div class="modal-header">
 <h5>Ajouter un nouvel ingrédient</h5>
 <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
 </div>
 <div class="modal-body">
 <form action="IngredientController" method="post">
 <div class="mb-3">
 <label class="form-label">Repas</label>
 <select class="form-control" id="mealId" name="mealId" required>
 <% if (meals != null) {
      for (Meal meal : meals) { %>
         <option value="<%= meal.getmealId() %>"><%= meal.getName() %></option>
 <% } } %>
 </select>
 </div>
 <div class="mb-3">
 <label class="form-label">Nom de l'ingrédient</label>
 <input type="text" class="form-control" id="ingredientName" name="name" required>
 </div>
 <div class="mb-3">
 <label class="form-label">Calories</label>
 <input type="number" class="form-control" id="ingredientCalories" name="calories" required>
 </div>
 <button type="submit" class="btn btn-success">Ajouter</button>
 </form>
 </div>
 </div>
 </div>
</div>




<!-- modifier modal  -->
<div class="modal fade" id="editIngredientModal" tabindex="-1" aria-hidden="true">
  <div class="modal-dialog">
    <form class="modal-content" action="EditIngredientServlet" method="post">
      <div class="modal-header">
        <h5 class="modal-title">Modifier l'ingrédient</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>
      <div class="modal-body">
        
        <!-- Choisir le repas -->
        <div class="mb-3">
          <label>Choisir un repas</label>
          <select class="form-control" id="mealSelect" name="mealId" required>
            <option value="">-- Choisir un repas --</option>
            <% if (meals != null) {
                 for (Meal meal : meals) { %>
                   <option value="<%= meal.getmealId() %>"><%= meal.getName() %></option>
            <% } } %>
          </select>
        </div>

        <!-- Choisir un ingrédient -->
        <div class="mb-3">
          <label>Choisir un ingrédient</label>
          <select class="form-control" name="ingredientId" id="ingredientSelect" required>
            <option value="">-- Choisir un ingrédient --</option>
            <% if (meals != null) {
                 for (Meal meal : meals) {
                   for (Ingredient ing : meal.getIngredients()) { %>
                     <option value="<%= ing.getId() %>" data-meal="<%= meal.getmealId() %>">
                       <%= ing.getName() %> (<%= meal.getName() %>)
                     </option>
            <%  } } } %>
          </select>
        </div>

        <!-- Modifier le nom de l'ingrédient -->
        <div class="mb-3">
          <label for="ingredientName" class="form-label">Nom de l'ingrédient</label>
          <input type="text" class="form-control" name="name" id="ingredientName" required>
        </div>

        <!-- Modifier les calories de l'ingrédient -->
        <div class="mb-3">
          <label for="ingredientCalories" class="form-label">Calories</label>
          <input type="number" class="form-control" name="calories" id="ingredientCalories" required>
        </div>
      </div>
      <div class="modal-footer">
        <button type="submit" class="btn btn-success">Modifier</button>
      </div>
    </form>
  </div>
</div>
















<!-- supprimer modal-->
<div class="modal fade" id="deleteIngredientModal" tabindex="-1" aria-hidden="true">
  <div class="modal-dialog">
    <form class="modal-content" action="DeleteIngredientController" method="post">
      <div class="modal-header">
        <h5 class="modal-title">Supprimer un ingrédient</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
      </div>
      <div class="modal-body">

        

        <!-- Choisir un ingrédient -->
        <div class="mb-3">
          <label>Choisir un ingrédient</label>
          <select class="form-control" name="ingredientId" id="ingredientSelect" required>
            <option value="">-- Choisir un ingrédient --</option>
            <% for (Meal meal : meals) {
                 for (Ingredient ing : meal.getIngredients()) { %>
                   <option value="<%= ing.getId() %>" data-meal="<%= meal.getmealId() %>">
                     <%= ing.getName() %> (<%= meal.getName() %>)
                   </option>
            <%  } } %>
          </select>
        </div>

      </div>
      <div class="modal-footer">
        <button type="submit" class="btn btn-danger">Supprimer</button>
      </div>
    </form>
  </div>
</div>

<script>
  document.getElementById('mealSelect').addEventListener('change', function () {
    const selectedMeal = this.value;
    const ingredientSelect = document.getElementById('ingredientSelect');

    // cacher tous les ingrédients
    Array.from(ingredientSelect.options).forEach(opt => {
      opt.style.display = opt.dataset.meal === selectedMeal ? 'block' : 'none';
    });

    ingredientSelect.value = ""; // reset selection
  });
</script>





<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>

