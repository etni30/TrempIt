var clicked2 = false;

  document.addEventListener('click', function(e){
    if(e.target && e.target.id== 'log_in'){
        myMoveR(left_collomn[0]);//change the screen to log-in
        //delete elements 'log-in', 'sign in'
        deleteElement();
        creatAnewForm();
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

//------------------------move screen function------------------------
function myMoveR(left_collomn) {
  var elem = left_collomn;
  elem.style.position = "relative";
  var pos = 0;
  var id = setInterval(frame, 5);

  document.getElementsByClassName('w3-twothird')[0].style.display = "none";
  function frame() {
    if (pos == 30) {
      clearInterval(id);
    } else {
      pos += 0.5;
      elem.style.left = pos + "%";
    }
  }
}


//------------------------move screen function------------------------
function myMoveL(left_collomn) {
  var elem = left_collomn;
  elem.style.position = "relative";
  var pos = 400;
  var id = setInterval(frame, 5);
  function frame() {
    if (pos == 0) {
      clearInterval(id);
      document.getElementsByClassName('w3-twothird')[0].style.display = "block";
      deleteElement()
      creatAccountElement()
    } else {
      pos -= 8;
      elem.style.left = pos + "px";// move left_collomn to the center
    }
  }
}
//------------------------creat a form------------------------
function creatAnewForm(){

  //change the title
  var img = document.createElement('img');
  img.src = "cssFile/images/button.png";
  img.id = "xbutton";
  left_collomn[0].getElementsByTagName('p')[0].innerHTML = "";
  left_collomn[0].getElementsByTagName('p')[0].appendChild(img);
  left_collomn[0].getElementsByTagName('p')[0].innerHTML += "Create your TrampIt Account";

  //insert new form to the project
  var form = document.createElement('form');//create a form sending the data
  form.action = 'connect_to_main_page.jsp';
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

//------------------------delet elements------------------------
function deleteElement(){
  // var x = register[0].getElementsByClassName('w3-container w3-center w3-round-xlarge w3-teal w3-hight');
  console.log();
  while (register[0].firstChild != null) {
    register[0].removeChild(register[0].firstChild);
  }

}

function creatAccountElement(){
  var div = document.createElement('div');
  div.classList.add("w3-container" ,"w3-center" ,"w3-round-xlarge" ,"w3-teal" ,"w3-hight");
  div.id = "sign_in";
  var a = document.createElement('a');
  div.innerHTML = "sign-in";
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
