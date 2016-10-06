<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@include file="/WEB-INF/jsp/wbem/pub/header-dialog.jsp" %>
 <table border="1" id="roomInfo">
	<c:if test="${maxFloor != 1 && maxFloor != 2}">
	<tr>
	 	<c:forEach items="${bldUnitInfoList}" var="bldUnitInfo" varStatus="status">
	 		<th colspan="${bldUnitInfo.doorNum}">${bldUnitInfo.unit}</th>
	 	</c:forEach>
	</tr>
	
	<c:forEach var="i" begin="${minFloor}" end="${maxFloor}">
		<tr>
			<c:forEach items="${bldUnitInfoList}" var="bldUnitInfo" varStatus="status">
				<c:if test="${bldUnitInfo.roomList[0].floor == maxFloor}">
					<c:set var="tdNum" value="0"/>
					<c:forEach var="index" begin="0" end="${fn:length(bldUnitInfo.roomList)}">
						<c:if test="${bldUnitInfo.roomList[index].floor == maxFloor+minFloor-i}">
							<c:if test="${bldUnitInfo.roomList[index].status == 0}">
								<td><input type="checkbox" name="roomGuid" id="${bldUnitInfo.roomList[index].roomGuid}" value="${bldUnitInfo.roomList[index].roomGuid}" unitNo="${bldUnitInfo.roomList[index].unitNo}" onclick="selectRoom(this)" style="margin:0;"><label style="float:right;font-size:12px;margin-bottom:0;" for="${bldUnitInfo.roomList[index].roomGuid}">${bldUnitInfo.roomList[index].room}</label></td>
							</c:if>
							<c:if test="${bldUnitInfo.roomList[index].status == 1}">
								<td style="background-color:#F7B557"><input type="checkbox" name="roomGuid" id="${bldUnitInfo.roomList[index].roomGuid}" value="${bldUnitInfo.roomList[index].roomGuid}" unitNo="${bldUnitInfo.roomList[index].unitNo}" onclick="selectRoom(this)" style="margin:0;"><label style="float:right;font-size:12px;margin-bottom:0;" for="${bldUnitInfo.roomList[index].roomGuid}">${bldUnitInfo.roomList[index].room}</label></td>
							</c:if>
							<c:if test="${bldUnitInfo.roomList[index].status == 2}">
								<td style="background-color:#57ADF7"><input type="checkbox" name="roomGuid" id="${bldUnitInfo.roomList[index].roomGuid}" value="${bldUnitInfo.roomList[index].roomGuid}" unitNo="${bldUnitInfo.roomList[index].unitNo}" onclick="selectRoom(this)" style="margin:0;"><label style="float:right;font-size:12px;margin-bottom:0;" for="${bldUnitInfo.roomList[index].roomGuid}">${bldUnitInfo.roomList[index].room}</label></td>
							</c:if>
							<c:set var="tdNum" value="${tdNum+1}"/>
						</c:if>
					</c:forEach>
					<c:forEach var="j" begin="${tdNum+1}" end="${bldUnitInfo.doorNum}">
						<td></td>
					</c:forEach>
				</c:if>
				<c:if test="${bldUnitInfo.roomList[0].floor < maxFloor}">
		 			<c:if test="${bldUnitInfo.roomList[0].floor < maxFloor+minFloor-i}">
		 				<c:forEach var="j" begin="1" end="${bldUnitInfo.doorNum}">
		 					<td></td>
		 				</c:forEach>	
		 			</c:if>
		 			<c:if test="${bldUnitInfo.roomList[0].floor >= maxFloor+minFloor-i}">
		 				<c:set var="tdNum" value="0"/>
						<c:forEach var="index" begin="0" end="${fn:length(bldUnitInfo.roomList)}">
							<c:if test="${bldUnitInfo.roomList[index].floor == maxFloor+minFloor-i}">
								<c:if test="${bldUnitInfo.roomList[index].status == 0}">
									<td><input type="checkbox" name="roomGuid" id="${bldUnitInfo.roomList[index].roomGuid}" value="${bldUnitInfo.roomList[index].roomGuid}" unitNo="${bldUnitInfo.roomList[index].unitNo}" onclick="selectRoom(this)" style="margin:0;"><label style="float:right;font-size:12px;margin-bottom:0;" for="${bldUnitInfo.roomList[index].roomGuid}">${bldUnitInfo.roomList[index].room}</label></td>
								</c:if>
								<c:if test="${bldUnitInfo.roomList[index].status == 1}">
									<td style="background-color:#F7B557"><input type="checkbox" name="roomGuid" id="${bldUnitInfo.roomList[index].roomGuid}" value="${bldUnitInfo.roomList[index].roomGuid}" unitNo="${bldUnitInfo.roomList[index].unitNo}" onclick="selectRoom(this)" style="margin:0;"><label style="float:right;font-size:12px;margin-bottom:0;" for="${bldUnitInfo.roomList[index].roomGuid}">${bldUnitInfo.roomList[index].room}</label></td>
								</c:if>
								<c:if test="${bldUnitInfo.roomList[index].status == 2}">
									<td style="background-color:#57ADF7"><input type="checkbox" name="roomGuid" id="${bldUnitInfo.roomList[index].roomGuid}" value="${bldUnitInfo.roomList[index].roomGuid}" unitNo="${bldUnitInfo.roomList[index].unitNo}" onclick="selectRoom(this)" style="margin:0;"><label style="float:right;font-size:12px;margin-bottom:0;" for="${bldUnitInfo.roomList[index].roomGuid}">${bldUnitInfo.roomList[index].room}</label></td>
								</c:if>
								<c:set var="tdNum" value="${tdNum+1}"/>
							</c:if>
						</c:forEach>
						<c:forEach var="j" begin="${tdNum+1}" end="${bldUnitInfo.doorNum}">
							<td></td>
						</c:forEach>
		 			</c:if>
				</c:if>
			</c:forEach>
		</tr>
	</c:forEach>
	</c:if>
	<c:if test="${maxFloor == 1}">
		<tr>
			<c:if test="${fn:length(bldUnitInfoList) == 2}">
				<c:set var="doorNum" value="6"/>
			 	<c:forEach items="${bldUnitInfoList}" var="bldUnitInfo" varStatus="status">
			 		<th colspan="${doorNum}">${bldUnitInfo.unit}</th>
			 	</c:forEach>
	 		</c:if>
	 		<c:if test="${fn:length(bldUnitInfoList) == 3}">
	 			<c:set var="doorNum" value="4"/>
			 	<c:forEach items="${bldUnitInfoList}" var="bldUnitInfo" varStatus="status">
			 		<th colspan="${doorNum}">${bldUnitInfo.unit}</th>
			 	</c:forEach>
	 		</c:if>
		</tr>
		
			<c:set var="maxDoorNum" value="0"/>
			<c:forEach items="${bldUnitInfoList}" var="bldUnitInfo" varStatus="status">
				<c:if test="${bldUnitInfo.doorNum > maxDoorNum}">
					<c:set var="maxDoorNum" value="${bldUnitInfo.doorNum}"/>
				</c:if>
			</c:forEach>
			<c:forEach var="i" begin="0" end="${maxDoorNum/doorNum}">
				<tr>
				<c:forEach items="${bldUnitInfoList}" var="bldUnitInfo" varStatus="statue">
					<c:forEach var="j" begin="0" end="${doorNum-1}">
						<c:if test="${i*doorNum+j+1 > fn:length(bldUnitInfo.roomList)}">
							<td></td>
						</c:if>
						<c:if test="${i*doorNum+j+1 <= fn:length(bldUnitInfo.roomList)}">
							<c:if test="${bldUnitInfo.roomList[i*doorNum+j].status == 0}">
								<td><input type="checkbox" name="roomGuid" id="${bldUnitInfo.roomList[i*doorNum+j].roomGuid}" value="${bldUnitInfo.roomList[i*doorNum+j].roomGuid}" unitNo="${bldUnitInfo.roomList[i*doorNum+j].unitNo}" onclick="selectRoom(this)" style="margin:0;"><label style="float:right;font-size:12px;margin-bottom:0;" for="${bldUnitInfo.roomList[i*doorNum+j].roomGuid}">${bldUnitInfo.roomList[i*doorNum+j].room}</label></td>
							</c:if>
							<c:if test="${bldUnitInfo.roomList[i*doorNum+j].status == 1}">
								<td style="background-color:#F7B557"><input type="checkbox" name="roomGuid" id="${bldUnitInfo.roomList[i*doorNum+j].roomGuid}" value="${bldUnitInfo.roomList[i*doorNum+j].roomGuid}" unitNo="${bldUnitInfo.roomList[i*doorNum+j].unitNo}" onclick="selectRoom(this)" style="margin:0;"><label style="float:right;font-size:12px;margin-bottom:0;" for="${bldUnitInfo.roomList[i*doorNum+j].roomGuid}">${bldUnitInfo.roomList[i*doorNum+j].room}</label></td>
							</c:if>
							<c:if test="${bldUnitInfo.roomList[i*doorNum+j].status == 2}">
								<td style="background-color:#57ADF7"><input type="checkbox" name="roomGuid" id="${bldUnitInfo.roomList[i*doorNum+j].roomGuid}" value="${bldUnitInfo.roomList[i*doorNum+j].roomGuid}" unitNo="${bldUnitInfo.roomList[i*doorNum+j].unitNo}" onclick="selectRoom(this)" style="margin:0;"><label style="float:right;font-size:12px;margin-bottom:0;" for="${bldUnitInfo.roomList[i*doorNum+j].roomGuid}">${bldUnitInfo.roomList[i*doorNum+j].room}</label></td>
							</c:if>
						</c:if>
					</c:forEach>
				</c:forEach>
				</tr>
			</c:forEach>
	</c:if>
	<c:if test="${maxFloor == 2}">
		<c:forEach items="${bldUnitInfoList}" var="bldUnitInfo" varStatus="status">
			<c:forEach var="i" begin="1" end="${fn:length(bldUnitInfo.roomList)}">
				<c:if test="${i%8 == 1}">
					<tr>
				</c:if>
					<c:if test="${bldUnitInfo.roomList[i-1].status == 0}">
						<td><input type="checkbox" name="roomGuid" id="${bldUnitInfo.roomList[i-1].roomGuid}" value="${bldUnitInfo.roomList[i-1].roomGuid}" unitNo="${bldUnitInfo.roomList[i-1].unitNo}" onclick="selectRoom(this)" style="margin:0;"><label style="float:right;font-size:12px;margin-bottom:0;" for="${bldUnitInfo.roomList[i-1].roomGuid}">${bldUnitInfo.roomList[i-1].room}</label></td>
					</c:if>
					<c:if test="${bldUnitInfo.roomList[i-1].status == 1}">
						<td style="background-color:#F7B557"><input type="checkbox" name="roomGuid" id="${bldUnitInfo.roomList[i-1].roomGuid}" value="${bldUnitInfo.roomList[i-1].roomGuid}" unitNo="${bldUnitInfo.roomList[i-1].unitNo}" onclick="selectRoom(this)" style="margin:0;"><label style="float:right;font-size:12px;margin-bottom:0;" for="${bldUnitInfo.roomList[i-1].roomGuid}">${bldUnitInfo.roomList[i-1].room}</label></td>
					</c:if>
					<c:if test="${bldUnitInfo.roomList[i-1].status == 2}">
						<td style="background-color:#57ADF7"><input type="checkbox" name="roomGuid" id="${bldUnitInfo.roomList[i-1].roomGuid}" value="${bldUnitInfo.roomList[i-1].roomGuid}" unitNo="${bldUnitInfo.roomList[i-1].unitNo}" onclick="selectRoom(this)" style="margin:0;"><label style="float:right;font-size:12px;margin-bottom:0;" for="${bldUnitInfo.roomList[i-1].roomGuid}">${bldUnitInfo.roomList[i-1].room}</label></td>
					</c:if>
				<c:if test="${i%8 == 0}">
					</tr>
				</c:if>
			</c:forEach>
			<c:forEach var="j" begin="1" end="${8-fn:length(bldUnitInfo.roomList)%8}" >
				<td></td>
			</c:forEach>
			
		</c:forEach>
	</c:if>
</table>
<%@include file="/wbem/include/include_base_js.jsp" %>
<%@include file="/wbem/include/include_tables_js.jsp" %>
<%@include file="/wbem/include/include_datepicker_js.jsp" %>