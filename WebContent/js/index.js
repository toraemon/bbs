$(document).ready(function(){
    			$('.clImg img:gt(0)').hide();
    			setInterval(function() {
        			$('.clImg :first-child').fadeOut().next('img').fadeIn().end().appendTo('.clImg');
    			},3000);
			});
			// 名前のチェック
			$('input[name=accountId]')
				.bind('blur', function(){
					if($(this).val()===''){
						$(this).css('background-color', '#fcc');
						//$(this).parent().append('<p class="caution">&nbsp;※この項目は必ずご記入ください</p>');
					}
				})
				.bind('focus', function(){
					if($(this).next()!==false){
						$(this).css('background-color', '#fff');
						$(this).removeClass('caution');
						$(this).next().remove();
					}
				}
			);
			// パスワードのチェック
			$('input[name=pass]')
				.bind('blur', function(){
					if($(this).val()===''){
						$(this).css('background-color', '#fcc');
						//$(this).parent().append('<p class="caution">&nbsp;※この項目は必ずご記入ください</p>');
					}
				})
				.bind('focus', function(){
					if($(this).next()!==false){
						$(this).css('background-color', '#fff');
						$(this).removeClass('caution');
						$(this).next().remove();
					}	
				}
			);