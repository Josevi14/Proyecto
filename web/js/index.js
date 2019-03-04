$(init);

function init() {
    var trs = document.getElementsByTagName("tr");
    for (var i = 1; i < trs.length; i++) {
        $("#fechaEntrada"+i).change(consultaFechas);
        $("#fechaSalida"+i).change(consultaFechas);
    }

    $("input[value=Reservar]").click(function (event) {
        event.preventDefault();
        var id = $(this).attr("id");
        id = id.substring(id.length - 1);
        anadirDias(id);
        $("form#" + id).submit();
    });
}

function consultaFechas() {
    var id = $(this).attr("id");
    var idHabitacion = $(this).parent().parent().attr("id");
    id = id.substring(id.length - 1);
    $("#reservar"+id).css("display","block");
    var fechaEntrada = new Date($("#fechaEntrada"+id).val()).getTime();
    var fechaSalida = new Date($("#fechaSalida"+id).val()).getTime();
    
    $.post("compararFecha.jsp", {
        idHabitacion: idHabitacion
    }, function (result){
        comprobarFechas(id, fechaEntrada, fechaSalida, result);
    }, "json");
}

function comprobarFechas(id, fechaEntrada, fechaSalida, result){
    var submit = $("#reservar"+id);
    for(var i = 0; i < result.length; i++){
        var fEntrada = new Date(result[i].fechaEntrada).getTime();
        var fSalida = new Date(result[i].fechaSalida).getTime();
        if((fechaEntrada >= fEntrada && fechaEntrada <= fSalida) || (fechaSalida <= fSalida && fechaSalida >= fEntrada) || (fechaEntrada <= fEntrada && fechaSalida >= fSalida)){
            submit.css("display","none");
        }
    }
}

function anadirDias(id) {
    var fechaEntrada = new Date($("#fechaEntrada" + id).val()).getTime();
    var fechaSalida = new Date($("#fechaSalida" + id).val()).getTime();
    var diff = fechaSalida - fechaEntrada;
    var diferencia = diff / (1000 * 60 * 60 * 24);

    var fila = document.getElementById(id);

    var input = document.createElement("input");
    input.setAttribute("type", "hidden");
    input.setAttribute("name", "dias");
    input.setAttribute("value", diferencia);
    fila.appendChild(input);
}
