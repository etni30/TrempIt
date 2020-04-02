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

    } else {
      pos -= 8;
      elem.style.left = pos + "px";
    }
  }
}
//------------------------creat a form------------------------
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
  form.action = 'connect_to_main_page.jsp';
  form.method = 'POST';
  
  form.innerHTML += "choose type:    driver <input type ='radio' value = 'driver' name ='type'/>  "
	  form.innerHTML += "passenger <input type ='radio' value = 'passenger' name ='type'/></br>"

  var input = document.createElement('input');//first name element
  input.name = "first";
  input.style.width = "100%";
  input.value = "first name";
  input.classList.add('w3-container','w3-center','w3-round-xlarge','w3-teal','w3-hight');
  form.appendChild(input);

  var input = document.createElement('input');//first name element
  input.name = "last";
  input.style.width = "100%";
  input.value = "last name";
  input.classList.add('w3-container','w3-center','w3-round-xlarge','w3-teal','w3-hight');
  form.appendChild(input);

  var input = document.createElement('input');//first name element
  input.name = "userName";
  input.style.width = "100%";
  input.value = "userName";
  input.classList.add('w3-container','w3-center','w3-round-xlarge','w3-teal','w3-hight');
  form.appendChild(input);

  var input = document.createElement('input');//password element
  input.name = "psw";
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
