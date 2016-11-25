$(document).ready(DateTimePicker_init('alert'));
$(document).ready(DateTimePicker_init('deadline'));

function DateTimePicker_init(name) {
    $('#'+name).daterangepicker({
        singleDatePicker: true,
        timePicker24Hour: true,
        timePicker: true,
        locale: {
            format: 'YYYY-MM-DD HH:mm'
        }
    });

    if (document.URL.indexOf("new") == 0) {
        clearInput('alert');
    }
}

function check() {

    if ($('#title').value == '' || $('#deadline').value == '') {
        $('#error-alert').removeClass('hidden');
        $('#title').addClass('alert alert-danger');
        $('#deadline').addClass('alert alert-danger');
        return false;
    }
}

function clearInput(name) {
    document.getElementsByName('alert')[0].value = '';
    return false;
}