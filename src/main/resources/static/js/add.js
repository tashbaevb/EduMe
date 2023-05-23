const commentFormContainer = document.getElementById('comment-form-container');
const commentFormToggle = document.getElementById('comment-form-toggle');
const commentForm = document.getElementById('comment-form');
const otherWidgets = document.getElementsByClassName('col-lg-12-1');

commentFormToggle.addEventListener('click', function() {
    commentForm.classList.toggle('show');

    // Смещение остальных виджетов при открытии формы
    if (commentForm.classList.contains('show')) {
        for (let i = 0; i < otherWidgets.length; i++) {
            otherWidgets[i].style.marginBottom = commentForm.clientHeight + 'px';
        }
    } else {
        for (let i = 0; i < otherWidgets.length; i++) {
            otherWidgets[i].style.marginBottom = '0';
        }
    }
});
