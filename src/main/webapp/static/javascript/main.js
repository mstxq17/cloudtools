$(function() {
    cards = document.querySelectorAll('.card');
});

function bg_change(next) {
    $("body").removeClass();
    $("body").addClass('is-' + next);
}

function view_change(next) {
    cards.forEach(card => {
        $(card).removeClass('is-show');
    });
    $(next).addClass('is-show');
}


function changeCard(e) {
    nextId = $(e).attr('data-target');
    next = $('#'+nextId);
    if (!next) {
        return;
    }
    bg_change(nextId);
    view_change(next);
}
