var animationComplete = true;
var currentScrollPercentage = 0.0;

function checkForMusic() {
    if (Math.round(currentScrollPercentage * 3) === 0) {
        $('audio#desktop1audio')[0].play();
        $('audio#desktop2audio')[0].pause();
        $('audio#desktop2audio')[0].currentTime = 0;
        $('audio#desktop3audio')[0].pause();
        $('audio#desktop3audio')[0].currentTime = 0;
        $('audio#desktop4audio')[0].pause();
        $('audio#desktop4audio')[0].currentTime = 0;
    }
    else if (Math.round(currentScrollPercentage * 3) === 1) {
        $('audio#desktop2audio')[0].play();
        $('audio#desktop1audio')[0].pause();
        $('audio#desktop1audio')[0].currentTime = 0;
        $('audio#desktop3audio')[0].pause();
        $('audio#desktop3audio')[0].currentTime = 0;
        $('audio#desktop4audio')[0].pause();
        $('audio#desktop4audio')[0].currentTime = 0;
    }
    else if (Math.round(currentScrollPercentage * 3) === 2) {
        $('audio#desktop3audio')[0].play();
        $('audio#desktop1audio')[0].pause();
        $('audio#desktop1audio')[0].currentTime = 0;
        $('audio#desktop2audio')[0].pause();
        $('audio#desktop2audio')[0].currentTime = 0;
        $('audio#desktop4audio')[0].pause();
        $('audio#desktop4audio')[0].currentTime = 0;
    }
    else if (Math.round(currentScrollPercentage * 3) === 3) {
        $('audio#desktop4audio')[0].play();
        $('audio#desktop1audio')[0].pause();
        $('audio#desktop1audio')[0].currentTime = 0;
        $('audio#desktop3audio')[0].pause();
        $('audio#desktop3audio')[0].currentTime = 0;
        $('audio#desktop2audio')[0].pause();
        $('audio#desktop2audio')[0].currentTime = 0;
    }
}

$(document).ready(function (){
    $('audio#desktop1audio').get(0).play();
    $("#screen").on("click", function() {
        if (animationComplete) {
            animationComplete = false;

            //GET CLIENT WIDTH AND HOW MUCH SHOULD THE PAGE SCROLL
            var currentScroll = $(window).scrollLeft();
            var clientWidth = document.documentElement.clientWidth
            var desiredScrollToRight = currentScroll + clientWidth;
            var desiredScrollToLeft = currentScroll - clientWidth;

            //GET MOUSE POSITION RELATIVE TO VIEWPORT
            var e = window.event;
            var posX = e.clientX;

            //PERFORM SCROLLING
            if (posX < clientWidth / 2) {
                $("html").animate({scrollLeft: desiredScrollToLeft}, 1000, function() {animationComplete = true; currentScrollPercentage = this.scrollLeft / (this.scrollWidth - this.offsetWidth);
                    console.log("Percentage: ", currentScrollPercentage, "Absolute: ", this.scrollLeft);
                    checkForMusic();
                });
            }
            else if (posX >= clientWidth / 2) {
                $("html").animate({scrollLeft: desiredScrollToRight}, 1000, function() {animationComplete = true; currentScrollPercentage = this.scrollLeft / (this.scrollWidth - this.offsetWidth)
                    console.log("Percentage: ", currentScrollPercentage, "Absolute: ", this.scrollLeft);
                    checkForMusic();
                });
            }
        }
    })

    //"HACK" TO DEAL WITH PESKY RESIZING
    $(window).on("resize", function() {
        console.log(currentScrollPercentage);
        $("html, body").animate({scrollLeft: Math.round((currentScrollPercentage * 3)) * document.documentElement.clientWidth}, 1);
    })
})