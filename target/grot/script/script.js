jQuery(document).ready(function () {

        var btn_close = $('.js-close-button');
        var btn_login = $('.signin');
        var btn_register = $('.signup');
        var form_register = $('.popup-register');
        var form_login = $('.popup-login');

        btn_login.click(function () {
          popup(form_login);
        });




        // $("#img-download").change(function () {
        //   readURL(this);
        // });
        

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

    // function readURL(input) {
    //   if (input.files && input.files[0]) {
    //     var reader = new FileReader();

    //     reader.onload = function (e) {
    //       $('#img-display').attr('src', e.target.result);
    //     }

    //     reader.readAsDataURL(input.files[0]);
    //   }
    // }