jQuery(document).ready(function () {
  
  $('#js-close-button').click(function () {
    if($('.popup').css('display') == 'none') {
      $('.popup').css("display", "block");
    } else {
      $('.popup').css("display", "none");
    }
  });
  $('.header_button').click(function () {
    if ($('.popup').css('display') == 'none') {
      $('.popup').css("display", "block");
    } else {
      $('.popup').css("display", "none");
    }
  });
  
  $(document).scroll(function() {
    navbarScroll();
  });
  
});

function navbarScroll() {
  var y = window.scrollY;
  if (y > 265) {
    $('.header').addClass('small');
  } else if (y < 265) {
    $('.header').removeClass('small');

  }
}