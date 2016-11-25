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
}

function check() {
    if ($('#title').val() == '' || $('#deadline').val() == '') {
        $('#error-alert').removeClass('hidden');
        $('#title').addClass('alert alert-danger');
        $('#deadline').addClass('alert alert-danger');
        return false;
    }
}

function clearInput(name) {
    $('#' + name).val('');
    return false;
}