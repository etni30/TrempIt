var clicked2 = false;

  document.addEventListener('click', function(e){
    if(e.target && e.target.id== 'log_in'){
        myMoveR(left_collomn[0]);//change the screen to log-in
        //delete elements 'log-in', 'sign in'
        deleteElement();
        creatAnewForm3();
        const xButton = left_collomn[0].getElementsByTagName('p')[0].getElementsByTagName('img')[0];
        clicked2 = true;
      }
    });

//---------------go back to menu---------------
document.addEventListener('click', function(e){
  if(e.target && e.target.id== 'xbutton'){
      if(clicked2){
        myMoveL(left_collomn[0]);
        left_collomn[0].getElementsByTagName('p')[0].textContent = "Account";
        clicked2 = false;
        sign_in = register[0].getElementsByTagName("div");
        }
      }
    });


//------------------------creat a form------------------------
function creatAnewForm3(){

  //change the title
  var img = document.createElement('img');
  img.src = "cssFile/images/button.png";
  img.id = "xbutton";
  left_collomn[0].getElementsByTagName('p')[0].innerHTML = "";
  left_collomn[0].getElementsByTagName('p')[0].appendChild(img);
  left_collomn[0].getElementsByTagName('p')[0].innerHTML += "Enter pasword & user name";

  //insert new form to the project
  var form = document.createElement('form');//create a form sending the data
  form.action = 'LogInServlet';
  form.method = 'POST';

  var input = document.createElement('input');//first name element
  input.name = "userName";
  input.required="required";
  input.style.width = "100%";
  input.value = "userName";
  input.classList.add('w3-container','w3-center','w3-round-xlarge','w3-teal','w3-hight');
  form.appendChild(input);

  var input = document.createElement('input');//password element
  input.name = "psw";
  input.required="required";
  input.value = "0000";
  input.style.width = "100%";
  input.type = "password";
  input.classList.add('w3-container','w3-center','w3-round-xlarge','w3-teal','w3-hight');
  form.appendChild(input);

  var input = document.createElement('input');//submit button element
  input.type = "submit";
  input.style.width = "100%";
  input.value = "submit";
  input.classList.add('w3-container','w3-center','w3-round-xlarge','w3-teal','w3-hight');
  form.appendChild(input);

  //append the form
  register[0].appendChild(form);

  const sign_in = register[0].getElementsByTagName("div");
}


//
function creatAccountElement33(){
  var div = document.createElement('div');
  div.classList.add("w3-container" ,"w3-center" ,"w3-round-xlarge" ,"w3-teal" ,"w3-hight");
  div.id = "sign_in";
  var a = document.createElement('a');
  div.innerHTML = "sign-up";
  register[0].appendChild(div);

  var div = document.createElement('div');
  div.classList.add("w3-container" ,"w3-center" ,"w3-round-xlarge" ,"w3-teal" ,"w3-hight");
  div.id = "log_in";
  register[0].innerHTML += "</br>";
  var a = document.createElement('a');
  div.innerHTML = "log-in";
  // div.appendChild(a);
  register[0].appendChild(div);
  
}
