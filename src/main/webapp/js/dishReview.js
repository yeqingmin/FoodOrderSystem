$(function(){
    // 绑定提交评论按钮的点击事件
    $('.submit-review').on('click', function() {
        // 获取页面元素的值
        var dishId = $(this).attr("dishId");
        var reviewText = $('.review-text').val();
        var rating = $('#rating').val();

        console.log(dishId)
        console.log(reviewText)
        console.log(rating)

        // 检查评论内容和评分是否已填写
        if (reviewText && rating) {
            // var data = {
            //     dishId: dishId, // 假设dishObj是包含菜品信息的对象
            //     reviewText: reviewText,
            //     rating: rating
            // };

            $.ajax({
                url: path+ '/jsp/dish', // 替换为实际的后端API路径
                data: {method: 'review', dishId: dishId, reviewText:reviewText,rating:rating},
                type: 'GET',
                contentType: 'application/json',
                success: function(response) {
                    // 请求成功，处理响应数据
                    console.log('Success:', response);
                    alert('评价成功！');
                },
                error: function(xhr, status, error) {
                    // 请求失败，处理错误
                    console.error('Error:', error);
                }
            });
        } else {
            // 提示用户填写评论内容和评分
            alert('请填写评论内容和评分！');
        }
    });
});