
document.addEventListener('DOMContentLoaded', function () {  
  chrome.tabs.query({ currentWindow: true, active: true }, function (tabs) {
    jQuery("#qrcodeCanvas").qrcode({
         width: 250,
         height: 250,
  		text	: tabs[0].url
  	});
  });
});

function checkForValidUrl(tab) { 
}

chrome.tabs.onUpdated.addListener(function(tabId, changeInfo, tab){
    if(changeInfo.status == "loading") {
        checkForValidUrl(tab);
    }
});

chrome.tabs.onSelectionChanged.addListener(function(tabId, selectInfo){
    chrome.tabs.getSelected(null, function(tab){
        checkForValidUrl(tab);
    });
});