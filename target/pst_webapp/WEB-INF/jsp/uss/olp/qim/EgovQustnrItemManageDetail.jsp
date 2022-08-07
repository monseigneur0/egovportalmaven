<%--
  Class Name : EgovQustnrItemManageDetail.jsp
  Description : 설문항목 상세보기
  Modification Information
 
      수정일         수정자                   수정내용
    -------    --------    ---------------------------
     2008.03.09    장동한          최초 생성
     2011.08.31  JJY       경량환경 버전 생성
     
    author   : 공통서비스 개발팀 장동한
    since    : 2009.03.09
   
--%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ui" uri="http://egovframework.gov/ctl/ui"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<c:set var="ImgUrl" value="/images_old/egovframework/com/uss/olp/qim/"/>
<%pageContext.setAttribute("crlf", "\r\n"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>샘플 포털 > 포털서비스관리 > 설문관리 > 설문항목</title>
	<meta charset="UTF-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<link rel="stylesheet" href="<c:url value='/'/>css/base.css">
	<link rel="stylesheet" href="<c:url value='/'/>css/layout.css">
	<link rel="stylesheet" href="<c:url value='/'/>css/component.css">
	<link rel="stylesheet" href="<c:url value='/'/>css/page.css">
	<script src="<c:url value='/'/>js/jquery-1.11.2.min.js"></script>
	<script src="<c:url value='/'/>js/ui.js"></script>

<script type="text/javaScript" language="javascript">


/* ********************************************************
 * 초기화
 ******************************************************** */
function fn_egov_init_QustnrItemManage(){

}
/* ********************************************************
 * 목록 으로 가기
 ******************************************************** */
function fn_egov_list_QustnrItemManage(){
	location.href = "<c:url value='/uss/olp/qim/EgovQustnrItemManageList.do'/>";
}
/* ********************************************************
 * 저장처리화면
 ******************************************************** */
function fn_egov_modify_QustnrItemManage(){
	var vFrom = document.QustnrItemManageForm;
	vFrom.cmd.value = '';
	vFrom.action = "<c:url value='/uss/olp/qim/EgovQustnrItemManageModify.do'/>";;
	vFrom.submit();

}
/* ********************************************************
 * 삭제처리
 ******************************************************** */
function fn_egov_delete_QustnrItemManage(){
	var vFrom = document.QustnrItemManageForm;
	if(confirm("삭제시   설문항목, 설문조사(설문결과)\n정보가 함께 삭제됩니다!\n\n삭제 하시겠습니까?")){
		vFrom.cmd.value = 'del';
		vFrom.action = "<c:url value='/uss/olp/qim/EgovQustnrItemManageDetail.d'/>o";
		vFrom.submit();
	}else{
		vFrom.cmd.value = '';
	}
}
</script>
</head>
<body onLoad="fn_egov_init_QustnrItemManage();">

    <!-- Skip navigation -->
    <a href="#contents" class="skip_navi">본문 바로가기</a>

    <div class="wrap">
        <!-- header start -->
	    <c:import url="/sym/mms/EgovHeader.do" />
	    <!-- //header end -->

        <div class="container">
            <div class="sub_layout">
                <div class="sub_in">
                    <div class="layout">
                        <!-- Left menu -->
	                    <c:import url="/sym/mms/EgovMenuLeft.do" />
	                    <!--// Left menu -->
        
                        <div class="content_wrap">
                            <div id="contents" class="content">
                                 <!-- Location -->
                                <div class="location">
                                    <ul>
                                        <li><a class="home" href="">Home</a></li>
                                        <li><a href="">포털서비스관리</a></li>
                                        <li><a href="">설문관리</a></li>
                                        <li>설문항목</li>
                                    </ul>
                                </div>
                                <!--// Location -->

                                <h1 class="tit_1">포털서비스관리</h1>

                                <p class="txt_1">포털시스템에서 제공되는 서비스들에 대한 컨텐츠를 관리합니다.</p>

                                <h2 class="tit_2">설문관리</h2>

                                <h3 class="tit_3">설문항목</h3>

                                <div class="board_view2">
                                
                                	<form name="QustnrItemManageForm" id="QustnrItemManageForm" action="<c:url value='/uss/olp/qim/EgovQustnrItemManageModify.do'/>" method="post">
                                
                                    <table>
                                        <colgroup>
                                            <col style="width: 190px;">
                                            <col style="width: auto;">
                                        </colgroup>
                                        <tr>
                                            <td class="lb">
                                                <span class="min">설문정보</span>
                                            </td>
                                            <td>
                                            	<c:out value="${fn:replace(resultList[0].qestnrCn , crlf , '<br/>')}" escapeXml="false" />
                                            	<input name="qestnrId" type="hidden" value="<c:out value="${resultList[0].qestnrId}" />">
                                            	<input name="qestnrTmplatId" type="hidden" value="<c:out value="${resultList[0].qestnrTmplatId}" />">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="lb">
                                                <span class="min">설문문항정보</span>
                                            </td>
                                            <td>
                                            	<c:out value="${fn:replace(resultList[0].qestnrQesitmCn , crlf , '<br/>')}" escapeXml="false" />
                                            	<input name="qestnrQesitmId" type="hidden" value="<c:out value="${resultList[0].qestnrQesitmId}" />">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="lb">
                                                <span class="min">질문 순번</span>
                                            </td>
                                            <td>
                                            	<c:out value="${resultList[0].iemSn}" />
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="lb">
                                                <span class="min">질문 내용</span>
                                            </td>
                                            <td>
                                            	<c:out value="${fn:replace(resultList[0].iemCn , crlf , '<br/>')}" escapeXml="false" />
                                            </td>
                                        </tr>
                                        <tr>
                                            <td class="lb">
                                                <span class="min">기타답변여부</span>
                                            </td>
                                            <td>
                                            	<c:out value=" ${resultList[0].etcAnswerAt}" />
                                            </td>
                                        </tr>
                                    </table>
                                    
                                    <input name="qustnrIemId" type="hidden" value="<c:out value="${resultList[0].qustnrIemId}" />">
									<input name="cmd" type="hidden" value="">
									<div style="visibility:hidden;display:none;"><input name="iptSubmit" type="submit" value="전송" title="전송"></div>
									
									</form>
                                    
                                </div>

								<!-- 목록/저장버튼  시작-->
                                <div class="board_view_bot">
                                    <div class="left_col btn3">
                                        <form name="formUpdt" action="<c:url value='/uss/olp/qim/EgovQustnrItemManageModify.do'/>" method="post" style="display: inline;">
                                        	<a href="#LINK" class="btn btn_skyblue_h46 w_100" onclick="fn_egov_modify_QustnrItemManage(); return false;"><spring:message code="button.update" /></a><!-- 수정 -->
                                        	<input name="qestnrTmplatId" type="hidden" value="${resultList[0].qustnrIemId}"/>
                                        </form>
                                        <form name="formDelete" action="<c:url value='/uss/olp/qim/EgovQustnrItemManageDetail.do'/>" method="post" style="display: inline; margin-left: 10px;">
                                        	<a href="#LINK" class="btn btn_skyblue_h46 w_100" onclick="fn_egov_delete_QustnrItemManage(); return false;"><spring:message code="button.delete" /></a><!-- 삭제 -->
                                        	<input name="qestnrTmplatId" type="hidden" value="${resultList[0].qustnrIemId}"/>
                                        	<input name="cmd" type="hidden" value="del"/>
                                        </form>
                                    </div>

                                    <div class="right_col btn1">
                                        <form name="formList" action="<c:url value='/uss/olp/qim/EgovQustnrItemManageList.do'/>" method="post">
                                        	<a href="#LINK" class="btn btn_blue_46 w_100" onclick="fn_egov_list_QustnrItemManage(); return false;"><spring:message code="button.list" /></a><!-- 목록 -->
                                        </form>
                                    </div>
                                </div>
                                <!-- 목록/저장버튼  끝-->
                                
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!-- footer 시작 -->
	    <c:import url="/sym/mms/EgovFooter.do" />
	    <!-- //footer 끝 -->
    </div>
    
</body>
</html>