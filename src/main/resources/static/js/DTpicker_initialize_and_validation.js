function page_load() {
    DateTimePicker_init('deadline');
    DateTimePicker_init('alert');
}

function DateTimePicker_init(name) {
    document.getElementsByName(name)[0].daterangepicker({
        singleDatePicker: true,
        timePicker24Hour: true,
        timePicker: true,
        locale: {
            format: 'YYYY-MM-DD HH:mm'
        }
    })
}
function check() {
    if (document.getElementsByName('title')[0].value == '' || document.getElementsByName('deadline')[0].value == '') {
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