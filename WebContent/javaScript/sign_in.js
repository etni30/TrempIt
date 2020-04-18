const right_contaner = document.getElementsByClassName('w3-twothird');
const left_collomn = document.getElementsByClassName('w3-third');
const register = document.getElementsByClassName('w3-light-grey w3-round-xlarge w3-small');
var sign_in = register[0].getElementsByTagName("div");
const xButton = left_collomn[0].getElementsByTagName('p')[0];
var clicked = false;

  document.addEventListener('click', function(e){
    if(e.target && e.target.id== 'sign_in'){
        myMoveR(left_collomn[0]);//change the screen to log-in
        //delete elements 'log-in', 'sign in'
        deleteElement();
        creatAnewForm1();
        const xButton = left_collomn[0].getElementsByTagName('p')[0].getElementsByTagName('img')[0];
        clicked = true;
      }
    });

//---------------go back to menu---------------
document.addEventListener('click', function(e){
  if(e.target && e.target.id== 'xbutton'){
      if(clicked){
        myMoveL(left_collomn[0]);
        left_collomn[0].getElementsByTagName('p')[0].textContent = "Account";
        clicked = false;
        sign_in = register[0].getElementsByTagName("div");
        }
      }
    });


////------------------------creat a form------------------------
function creatAnewForm1(){

  //change the title
  var img = document.createElement('img');
  img.src = "cssFile/images/button.png";
  img.id = "xbutton";
  left_collomn[0].getElementsByTagName('p')[0].innerHTML = "";
  left_collomn[0].getElementsByTagName('p')[0].appendChild(img);
  left_collomn[0].getElementsByTagName('p')[0].innerHTML += "Create your TrampIt Account";

  //insert new form to the project
  var form = document.createElement('form');//creat a form sending the data
  form.action = 'SignInServlet';
  form.method = 'post';
  
  form.innerHTML += "choose type:    driver <input type ='radio' value = 'driver' name ='type' required='required' />  "
	  form.innerHTML += "passenger <input type ='radio' value = 'passenger' name ='type' required='required'/></br>"

  var input = document.createElement('input');//first name element
  input.name = "first";
  input.required="required";
  input.style.width = "100%";
  input.value = "insert first name";
  input.classList.add('w3-container','w3-center','w3-round-xlarge','w3-teal','w3-hight');
  form.appendChild(input);

  var input = document.createElement('input');//first name element
  input.name = "last";
  input.required="required"
  input.style.width = "100%";
  input.value = "insert last name";
  input.classList.add('w3-container','w3-center','w3-round-xlarge','w3-teal','w3-hight');
  form.appendChild(input);

  var input = document.createElement('input');//first name element
  input.name = "userName";
  input.required="required"
  input.style.width = "100%";
  input.value = "insert userName";
  input.classList.add('w3-container','w3-center','w3-round-xlarge','w3-teal','w3-hight');
  form.appendChild(input);

  var input = document.createElement('input');//password element
  input.name = "psw";
  input.required="required"
  input.value = "0000";
  input.style.width = "100%";
  input.type = "password";
  input.classList.add('w3-container','w3-center','w3-round-xlarge','w3-teal','w3-hight');
  form.appendChild(input);

  var input = document.createElement('input');//password element
  input.name = "mail";
  input.required="required"
  input.value = "insert mail for communication";
  input.style.width = "100%";
  input.type = "text";
  input.classList.add('w3-container','w3-center','w3-round-xlarge','w3-teal','w3-hight');
  form.appendChild(input);
  
  var input = document.createElement('input');//submit button element
  input.type = "submit";
  input.required="required"
  input.style.width = "100%";
  input.value = "submit";
  input.classList.add('w3-container','w3-center','w3-round-xlarge','w3-teal','w3-hight');
  form.appendChild(input);

  //append the form
  register[0].appendChild(form);

}


