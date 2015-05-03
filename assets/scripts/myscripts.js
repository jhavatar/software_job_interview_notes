getObjectKeys = function (object)
{
  var keys = [];
  for(var i in object) if (object.hasOwnProperty(i))
  {
    keys.push(i);
  }
  return keys;
}

function getUrlVars()
{
    var vars = [];
    var hashes = window.location.href.slice(window.location.href.indexOf('?') + 1).split('&');
    for(var i = 0; i < hashes.length; i++)
    {
        var hash = hashes[i].split('=');
        vars.push(hash[0]);
        vars[hash[0]] = hash[1];
    }
    
    console.log("vars = " + getObjectKeys(vars));
    
    return vars;
}



function loadTOC() {
   $('#toc').toc({
   	'container': '#content',
		'selectors': 'h2,h3', //elements to use as headings
		'smoothScrolling': true, //enable or disable smooth scrolling on click
    'prefix': 'toc', //prefix for anchor tags and class names
		});
}



function setSubsections(urlVars)
{
	var pageKey = "page";
	if (!urlVars.hasOwnProperty(pageKey))
		return;
	var page = parseInt(urlVars[pageKey]);
	console.log("page = " + page);
		
	var subsectionBase = "subsection";
	for (var i=0; i < 999; i++)
	{
		subsectionId = subsectionBase + i;
		var button = document.getElementById(subsectionId);
		if (!button)
			break;
		
		console.log("button = " + button);
		targetHtml = $(button).attr("targetHtml");
		var targetPage = urlVars[targetHtml];
		var targetPageNumbering = urlVars[targetPage];
		console.log("targetHtml = " + targetHtml + ", targetPage = " + targetPage + ", targetPageNumbering = " + targetPageNumbering);
		$(button).attr("onclick","window.location.assign('http://gotoPage(" + targetPage + ")')");
		
		var h = $(button).find("h2");
		if (!h)
			h = $(button).find("h3");
		if (!h)
			h = $(button).find("h4");
		console.log("h = " + h);
		
		h.html(targetPageNumbering + " " + h.html());
		
	}
	
//	var clickBase = "click";
//	
//	for (var i=page+1; i < 999; i++)
//	{
//		for (var j=0; j < 999; j++)
//		{
//			var k = i + j;
//			console.log("i = " + i + ", j = " + j + ", k = " + k);
//			var numberingKey = numberingBase+k;
//			if (!urlVars.hasOwnProperty(numberingKey))
//				return;
//			var numbering = urlVars[numberingKey];
//			
//			var numberingId = numberingBase + j;
//			var clickId = clickBase + j;
//			
//			
//			var elemTitle = document.getElementById(numberingId);
//			$(elemTitle).html(numbering + " " + $(elemTitle).html());
//			
//			var elemClick = document.getElementById(clickId);
//			$(elemClick).attr("onclick","window.location.assign('http://gotoPage(" + k + ")')");
//		}
//	}
}

function setSubsectionsNumbering(urlVars)
{
	var pageKey = "page";
	if (!urlVars.hasOwnProperty(pageKey))
		return;
	var page = parseInt(urlVars[pageKey]);
	console.log("page = " + page);
		
	var numberingBase = "numbering";
	var clickBase = "click";
	
	for (var i=page+1; i < 999; i++)
	{
		for (var j=0; j < 999; j++)
		{
			var k = i + j;
			console.log("i = " + i + ", j = " + j + ", k = " + k);
			var numberingKey = numberingBase+k;
			if (!urlVars.hasOwnProperty(numberingKey))
				return;
			var numbering = urlVars[numberingKey];
			
			var numberingId = numberingBase + j;
			var clickId = clickBase + j;
			
			
			var elemTitle = document.getElementById(numberingId);
			$(elemTitle).html(numbering + " " + $(elemTitle).html());
			
			var elemClick = document.getElementById(clickId);
			$(elemClick).attr("onclick","window.location.assign('http://gotoPage(" + k + ")')");
		}
		
//		
//		var id = idBase + i;
//		var click = clickBase + i;
//		console.log(" click = " + click + ", id = " + id);
//		if (urlVars.hasOwnProperty(id))
//		{
//			var elemSubsection = document.getElementById(id);
//			//console.log("h2 = " + h2); 
//			$(elemSubsection).html(urlVars[id] + " " + $(elemSubsection).html());
//			var clickSubsection = document.getElementById(click);
//			$(clickSubsection).attr("onclick","window.location.assign('http://gotoPage(" + urlVars[click] + ")')");
//		}
//		else
//			break;
	}
}
