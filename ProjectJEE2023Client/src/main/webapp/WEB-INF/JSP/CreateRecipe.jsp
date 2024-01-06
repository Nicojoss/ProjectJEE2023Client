<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Create Recipe</title>
    <script>
    function addIngredientFields() {
        var container = document.getElementById('ingredientFields');
        
        var ingredientNameField = document.createElement('input');
        ingredientNameField.type = 'text';
        ingredientNameField.name = 'ingredientNames';
        ingredientNameField.value = '';
        ingredientNameField.size = '20';
        
        var ingredientTypeField = document.createElement('select');
        ingredientTypeField.name = 'ingredientTypes';
        var types = ['Fruit', 'Vegetable', 'Spicy', 'Other'];
        for (var i = 0; i < types.length; i++) {
            var option = document.createElement('option');
            option.value = types[i];
            option.text = types[i];
            ingredientTypeField.appendChild(option);
        }
        ingredientTypeField.value = 'Other';
        var ingredientQuantityField = document.createElement('input');
        ingredientQuantityField.type = 'text';
        ingredientQuantityField.name = 'ingredientQuantities';
        ingredientQuantityField.value = '';
        ingredientQuantityField.size = '10';
        container.appendChild(ingredientNameField);
        container.appendChild(ingredientTypeField);
        container.appendChild(ingredientQuantityField);
    }

    function addField(containerId, fieldName) {
        if (fieldName === 'ingredientName') {
            addIngredientFields();
        } else if (fieldName === 'stepInstruction') {
            var instructionField = document.createElement('textarea');
            instructionField.name = 'stepInstructions';
            instructionField.rows = '3';
            instructionField.cols = '30';
            container.appendChild(instructionField);
        } else {
            var container = document.getElementById(containerId);
            var field = document.createElement('input');
            field.type = 'text';
            field.name = fieldName;
            field.value = '';
            field.size = '20';
            container.appendChild(field);
        }
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
                    <input type="text" name="recipeName" id="recipeName" value="" size="20" required/>
                </td>
            </tr>
            <tr>
                <td>Recipe Gender: </td>
                <td>
                    <select name="recipeGender">
                        <option value="Entree" selected>Entree</option>
                        <option value="Dish">Dish</option>
                        <option value="Desserts">Desserts</option>
                        <option value="Cocktails">Cocktails</option>
                        <option value="VegetarianDishes">Vegetarian Dishes</option>
                    </select>
                </td>
            </tr>
			<tr>
    			<td colspan="2">
       				<h3>Ingredients</h3>
    			</td>
			</tr>
			<tr id="ingredientFields">
    			<td>Ingredient Name: </td>
    			<td>
        			<input type="text" name="ingredientName" value="" size="20" required/>
    			</td>
			</tr>
			<tr>
    		<td>Ingredient Type: </td>
    			<td>
        			<select name="ingredientType">
            		<option value="Fruit">Fruit</option>
            		<option value="Vegetable">Vegetable</option>
            		<option value="Spicy">Spicy</option>
            		<option value="Other" selected>Other</option>
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
       				<button type="button" onclick="addIngredientFields()">Add Ingredient</button>
    			</td>
			</tr>
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
