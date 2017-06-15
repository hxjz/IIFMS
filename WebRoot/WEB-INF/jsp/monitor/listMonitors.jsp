<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="/WEB-INF/jsp/common/common.jsp" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>涉案财物-移动监控页</title>
    <link rel="stylesheet" type="text/css" href="${path }/css/video-js.css">
    <script src="${path }/js/video/ie8/videojs-ie8.js"></script>
    <script>
        videojs.options.flash.swf = "http://example.com/path/to/video-js.swf"
    </script
</head>
<body>
<div class="t_rightcontainer">
    <div class="t_oneblock">
        <div class="t_oneblock_h">
            <h3>移动监控显示</h3>
        </div>
        <table>
            <tr>
                <td colspan="5">
                    <div class="m">
                        <%--vjs-big-play-centered--%>
                        <video id="my-video" class="video-js" controls preload="auto" width="600" height="400"
                               poster="http://images.csdn.net/20170613/VCG21gic11332258.jpg" data-setup="{}">
                            <source id=“videoSource” src="${videoUrl}" type="video/${videoType}">
                            <%--<source src="http://vjs.zencdn.net/v/oceans.mp4" type="video/mp4">--%>
                            <%--<source src="http://vjs.zencdn.net/v/oceans.webm" type="video/webm">--%>
                            <%--<source src="http://vjs.zencdn.net/v/oceans.ogv" type="video/ogg">--%>
                            <p class="vjs-no-js">
                                您的浏览器不支持查看视频，使启用JavaScript或者考虑升级浏览器。
                                <a href="http://videojs.com/html5-video-support/" target="_blank">支持的浏览器</a>
                            </p>
                        </video>
                    </div>
                </td>
            </tr>
            <tr>
                <td>&nbsp;</td>
                <td>
                    <button class="t_btnsty03" id="pause">暂停</button>
                </td>
                <td>
                    <button class="t_btnsty03" id="play">播放</button>
                </td>
                <td>
                    <button class="t_btnsty03" id="kuai">快进</button>
                </td>
                <td>&nbsp;</td>
            </tr>

        </table>

    </div>
</div>
<script src="${path }/js/video/video.js"></script>
<script type="text/javascript">
    $(function () {
        //        var myPlayer = videojs('my-video');
        //        videojs("my-video").ready(function () {
        //            var myPlayer = this;
        //          myPlayer.play();
        //        });
        var player = videojs('my-video');

        $('#pause').click(function () {
            player.pause();
        });
        $('#play').click(function () {
            player.play();
        });
        $('#kuai').click(function () {
            var whereYouAt = player.duration();
            console.log(whereYouAt);
            player.setCurrentTime(whereYouAt + 10);
        });
    });
</script>
</body>
</html>
