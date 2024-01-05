<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Recipe</title>
    <script>
        function addField(containerId, fieldName) {
            var container = document.getElementById(containerId);
            var field = document.createElement("input");
            field.type = "text";
            field.name = fieldName;
            field.value = "";
            field.size = "20";
            container.appendChild(field);
        }
    </script>
</head>
<body>
    <a href="/ProjectJEE2023Client/HomeServlet">Home Page</a>
    <form action="CreateRecipeServlet" method="POST">
        <table border="1" cellspacing="0" cellpadding="5">
            <tr>
                <td>Recipe Name: </td>
                <td>
                    <input type="text" name="recipeName" id="recipeName" value="" size="20"/>
                </td>
            </tr>
            <tr>
                <td>Recipe Gender: </td>
                <td>
                    <select name="recipeGender">
                        <option value="Entree">Entree</option>
                        <option value="Dish">Dish</option>
                        <option value="Desserts">Desserts</option>
                        <option value="Cocktails">Cocktails</option>
                        <option value="VegetarianDishes">Vegetarian Dishes</option>
                    </select>
                </td>
            </tr>
            <!-- Ingredients Section -->
            <tr>
                <td colspan="2">
                    <h3>Ingredients</h3>
                </td>
            </tr>
            <tr id="ingredientFields">
                <td>Ingredient Name: </td>
                <td>
                    <input type="text" name="ingredientName" value="" size="20"/>
                </td>
            </tr>
            <tr>
                <td>Ingredient Type: </td>
                <td>
                    <select name="ingredientType">
                        <option value="Fruit">Fruit</option>
                        <option value="Vegetable">Vegetable</option>
                        <option value="Spicy">Spicy</option>
                        <option value="Other">Other</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td>Ingredient Quantity: </td>
                <td>
                    <input type="text" name="ingredientQuantity" value="" size="10"/>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <button type="button" onclick="addField('ingredientFields', 'ingredientName')">Add Ingredient</button>
                </td>
            </tr>
            <!-- Recipe Steps Section -->
            <tr>
                <td colspan="2">
                    <h3>Recipe Steps</h3>
                </td>
            </tr>
            <tr id="recipeStepFields">
                <td>Step Instruction: </td>
                <td>
                    <textarea name="stepInstruction" rows="3" cols="30"></textarea>
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <button type="button" onclick="addField('recipeStepFields', 'stepInstruction')">Add Recipe Step</button>
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" name="submit" value="Create Recipe"/>
                </td>
            </tr>
        </table>
    </form>
</body>
</html>
