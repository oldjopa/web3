const canvas = document.getElementById('myCanvas');
const ctx = canvas.getContext('2d');
const selectedColor = "#2a5e2c"

document.addEventListener("DOMContentLoaded", function () {
  const r = document.getElementById("input-form:r_spinner_input").value;
  drawCanvas(r);
  drawPoints(true);
  // кнопочки
  Array.from(document.getElementsByClassName("commandLink")).forEach(e => {
    e.addEventListener("click", () => {
      Array.from(document.getElementsByClassName("commandLink")).forEach(b => { b.style.background = "#4CAF50" })
      e.style.background = selectedColor;
    });
    var x = Number(document.getElementById("bean_x").innerText);
    if (e.innerText== x){
      e.style.background = selectedColor;
    }
  })

});



function onCanvasClick(event) {
  var x = event.clientX - canvas.getBoundingClientRect().left;
  var y = event.clientY - canvas.getBoundingClientRect().top;
  var r = document.getElementById("input-form:r_spinner_input").value;
  x = (x - 200) / 60;
  y = -(y - 200) / 60;
  drawPoint(x, y, validate(x,y,r) ? "green" : "red");
  document.getElementById("hidden-canvas-form:x").value = x;
  document.getElementById("hidden-canvas-form:y").value = y;
  document.getElementById("hidden-canvas-form:r").value = r;
  return true;
}

function drawPoint(x, y, color) {
  x = 60 * x + 200;
  y = -60 * y + 200;
  ctx.beginPath();
  ctx.arc(x, y, 3, 0, 2 * Math.PI);
  ctx.fillStyle = color;
  ctx.fill();
}

function radiusChange() {
  var r= document.getElementById("input-form:r_spinner_input").value;
  if(r>3){r=3;}
  if(r<0.1){r=0.1;}
  drawCanvas(r);
  drawPoints(true);
}

function drawPoints(radiusChanged = false) {
  const r = document.getElementById("input-form:r_spinner_input").value;
  const table_body = document.getElementById("dots-table").getElementsByTagName("table")[1].tBodies[0];
  for (let i = 0; i < table_body.rows.length; i++) {
    if (table_body.rows[i].childElementCount >= 3) {
      const point = {
        x: Number(table_body.rows[i].children[0].innerText),
        y: Number(table_body.rows[i].children[1].innerText)
      };
      var color = "red";
      if (radiusChanged) {
        if (validate(point.x, point.y, r)) { color = "green"; }
      }
      drawPoint(point.x, point.y, color);
    }
  }
}

function validate(x, y, r) {
  return (x >= 0 && y < 0 && x *x + y *y < r *r) || 
  (x >= 0 && y >= 0 && x < r && y < r / 2) || 
  (x < 0 && y < 0 && x + y + r/2 > 0);
}

function onFormSubmit(){
  var r = document.getElementById("input-form:r_spinner_input").value;
  var x = Number(document.getElementById("bean_x").innerText);
  var y = document.getElementById("input-form:y_spinner_input").value;
  drawPoint(x, y, validate(x,y,r) ? "green" : "red");
}

function clear(){
  const r = document.getElementById("input-form:r_spinner_input").value;
  drawCanvas(r);
}
