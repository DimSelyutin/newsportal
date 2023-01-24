jQuery(document).ready(function () {

  var btn_close = $('.js-close-button');
  var btn_login = $('.signin');
  var btn_register = $('.signup');
  var form_register = $('.popup-register');
  var form_login = $('.popup-login');
  var like_btn = $('.like-svg');


  $('.msg_close').click(function () {
    $('.msg').css({ 'display': 'none' }, 2000);
  });

  $('.msg').animate({ 'top': '100px' }, 500);
  $('.msg').animate({ 'opacity': '0' }, 8000);



  btn_login.click(function () {
    popup(form_login);
  });
  btn_register.click(function () {
    popup(form_register);
  });


  like_btn.click(function () {
    if ($(this).hasClass('like-svg-active')) {
      $(this).removeClass('like-svg-active');
    } else {
      $(this).addClass('like-svg-active');
    }
  });

  $("#img-download").change(function () {
    readURL(this);
  });


  $(".menu-icons").delegate("div", "click", function (e) {

    $(this).find('.hidden_menu').css("display", "grid");

  });

  $(".menu-svg").delegate("svg", "click", function (e) {

    $(this).css("fill", "#d40000");

  });

  $(document).mouseup(function (e) {
    var container = $('.hidden_menu');
    if (container.has(e.target).length === 0) {
      container.hide();
    }
  });

  btn_close.click(function () {
    if (form_login.css('display') == 'block') {

      form_login.css("display", "none");
    }
    if (form_register.css('display') == 'block') {

      form_register.css("display", "none");
    }
  });


  $(document).scroll(function () {
    navbarScroll();
  });


  $(".like-svg-container").bind("click", function () {
    var link = $(this);
    var id = link.data('idNews');
    var nam = link.val();
    console.log(id);
    console.log(nam);
    $.ajax({
      url: "controller?command=do_add_like",
      type: "GET",
      data: {id : link.val()}, // Передаем ID нашей статьи
      dataType: "json",
      success: function (result) {
        if (!result.error) { //если на сервере не произойло ошибки то обновляем количество лайков на странице
          link.addClass('like-svg-active'); // помечаем лайк как "понравившийся"
          $('.counter', link).html(result.count);
        } else {
          console.log(result.message);
        }
      }
    });

  });
  
  $('.answer_comment').click(function(){
    $('#wmd-input').val($('#wmd-input').val() + 'more text');
  });
});

function popup(form_any) {
  if (form_any.css('display') == 'none') {
    form_any.css("display", "block");
  } else {
    form_any.css("display", "none");
  }

}

function navbarScroll() {
  var y = window.scrollY;
  if (y > 265) {
    $('.header').addClass('small');
  } else if (y < 265) {
    $('.header').removeClass('small');

  }




}

function readURL(input) {
  if (input.files && input.files[0]) {
    var reader = new FileReader();

    reader.onload = function (e) {
      $('#img-display').attr('src', e.target.result);
    }

    reader.readAsDataURL(input.files[0]);
  }
}