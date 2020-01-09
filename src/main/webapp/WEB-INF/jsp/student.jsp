<%@taglib uri = "http://www.springframework.org/tags/form" prefix = "form"%>
<%@taglib prefix="botDetect" uri="https://captcha.com/java/jsp"%>
<html>
   <head>
      <title>Spring MVC Form Handling</title>
   </head>

   <body>
      <h2>Student Information</h2>
      <form:form method = "POST" action = "/spring-mvc/web/student/">
         <table>
            <tr>
               <td><form:label path = "name">Name</form:label></td>
               <td><form:input path = "name" /></td>
            </tr>
            <tr>
               <td><form:label path = "age">Age</form:label></td>
               <td><form:input path = "age" /></td>
            </tr>
            <tr>
               <td><form:label path = "id">id</form:label></td>
               <td><form:input path = "id" /></td>
            </tr>
            <tr>
               <td colspan = "2">
                  
               </td>
            </tr>
         </table>  
         
         <botDetect:captcha id="exampleCaptcha" userInputID="captchaCode"/>

		<div class="validationDiv">
            <form:input path = "captchaCode" />
		</div>
        <input type = "submit" value = "Submit"/>
      </form:form>
   </body>
   
</html>
