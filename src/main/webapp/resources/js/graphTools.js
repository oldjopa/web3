

function drawCanvas(radius) {
        unit = 30*radius
        const canvas = document.getElementById('myCanvas');
        const ctx = canvas.getContext('2d');
        ctx.clearRect(0, 0, canvas.width, canvas.height)
        var center_x=200;
        var center_y=200

        // Задаем белый фон
        ctx.fillStyle = 'white';
        ctx.fillRect(0, 0, canvas.width, canvas.height);

        // Рисуем стрелки
        ctx.fillStyle = 'black';
        ctx.beginPath();
        ctx.moveTo(195, 10);
        ctx.lineTo(200, 0);
        ctx.lineTo(205, 10);
        ctx.closePath();
        ctx.fill();

        ctx.beginPath();
        ctx.moveTo(390, 195);
        ctx.lineTo(400, 200);
        ctx.lineTo(390, 205);
        ctx.closePath();
        ctx.fill();

        // Рисуем единичные отрезки
        ctx.font = '12px Arial';
        ctx.fillText('1', 210, 215);
        ctx.fillText('1', 190, 215);
        ctx.fillText('1', 375, 190);
        ctx.fillText('1', 375, 210);

        // Рисуем прямоугольник
        ctx.fillStyle = 'lightblue';
        ctx.strokeStyle = 'black';
        ctx.lineWidth = 2;
        ctx.fillRect(center_x, center_y-unit, 2*unit, unit);
        ctx.stroke();


        // Рисуем треугольник
        ctx.fillStyle = 'lightblue';
        ctx.strokeStyle = 'black';
        ctx.lineWidth = 2;
        ctx.beginPath();
        ctx.moveTo(center_x, center_y);
        ctx.lineTo(center_x-unit, center_y);
        ctx.lineTo(center_x, center_y+unit);
        ctx.closePath();
        ctx.stroke();
        ctx.fill();

        // Рисуем сектор
        ctx.fillStyle = 'lightblue';
        ctx.strokeStyle = 'black';
        ctx.lineWidth = 2;
        ctx.beginPath();
        ctx.moveTo(center_x, center_y);
        ctx.arc(center_x, center_y, 2*unit, 0, Math.PI / 2, false);
        ctx.stroke();
        ctx.closePath();
        ctx.fill();

        // Рисуем вертикальную линию
        ctx.beginPath();
        ctx.moveTo(200, 0);
        ctx.lineTo(200, 400);
        ctx.strokeStyle = 'black';
        ctx.lineWidth = 3;
        ctx.stroke();

        // Рисуем горизонтальную линию
        ctx.beginPath();
        ctx.moveTo(0, 200);
        ctx.lineTo(400, 200);
        ctx.strokeStyle = 'black';
        ctx.lineWidth = 3;
        ctx.stroke();
}