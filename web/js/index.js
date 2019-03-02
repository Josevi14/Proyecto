window.onload = initJS;

function initJS(){
    var selectDiaEntrada = document.getElementById("diaEntrada");
    var selectDiaSalida = document.getElementById("diaSalida");
    var selectMesEntrada = document.getElementById("mesEntrada");
    var selectMesSalida = document.getElementById("mesSalida");
    var selectAnioEntrada = document.getElementById("anioEntrada");
    var selectAnioSalida = document.getElementById("anioSalida");
    
    var meses = "Enero, Febrero, Marzo, Abril, Mayo, Junio, Julio, Agosto, Septiembre, Octubre, Noviembre, Diciembre";
    meses = meses.split(",");
    var anios = "2019, 2020, 2021";
    anios = anios.split(",");
    
    for(var i = 1; i <= 31; i++){
        var option = document.createElement("option");
        var texto = document.createTextNode(i);
        option.appendChild(texto);
        var option2 = document.createElement("option");
        var texto2 = document.createTextNode(i);
        option2.appendChild(texto2);
        selectDiaEntrada.appendChild(option);
        selectDiaSalida.appendChild(option2);
    }
    
    for(var i = 0; i < meses.length; i++){
        var option = document.createElement("option");
        var texto = document.createTextNode(meses[i]);
        option.appendChild(texto);
        var option2 = document.createElement("option");
        var texto2 = document.createTextNode(meses[i]);
        option2.appendChild(texto2);
        selectMesEntrada.appendChild(option);
        selectMesSalida.appendChild(option2);
    }
    
    for(var i = 0; i < anios.length; i++){
        var option = document.createElement("option");
        var texto = document.createTextNode(anios[i]);
        option.appendChild(texto);
        var option2 = document.createElement("option");
        var texto2 = document.createTextNode(anios[i]);
        option2.appendChild(texto2);
        selectAnioEntrada.appendChild(option);
        selectAnioSalida.appendChild(option2);
    }
    initJquery();
}

function initJquery(){
    $("#diaEntrada").click(function(){
        consultaFechas();
    });
    $("#diaSalida").click(function(){
        consultaFechas();
    });
    $("#mesEntrada").click(function(){
        consultaFechas();
    });
    $("#mesSalida").click(function(){
        consultaFechas();
    });
    $("#anioEntrada").click(function(){
        consultaFechas();
    });
    $("#anioSalida").click(function(){
        consultaFechas();
    });
}

function consultaFechas(){
    
}