var map = L.map('map').setView([54, -2], 6);
var markers = new L.FeatureGroup();
map.addLayer(markers);

var MB_ATTR = 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, ' +
'<a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, ' +
'Imagery © <a href="http://mapbox.com">Mapbox</a>';

var MB_URL = 'http://{s}.tiles.mapbox.com/v3/{id}/{z}/{x}/{y}.png';

var OSM_URL = 'http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png';
var OSM_ATTRIB = '&copy; <a href="http://openstreetmap.org/copyright">OpenStreetMap</a> contributors';

L.tileLayer(MB_URL, {attribution: MB_ATTR, id: 'examples.map-i875mjb7'}).addTo(map);



// var circle = L.circle([51.508, -0.11], 50000, {
//   color: 'red',
//   fillColor: '#f03',
//   fillOpacity: 0.5
// }).addTo(map);

function round(value, exp) {
  if (typeof exp === 'undefined' || +exp === 0)
    return Math.round(value);

    value = +value;
    exp  = +exp;

    if (isNaN(value) || !(typeof exp === 'number' && exp % 1 === 0))
      return NaN;

      // Shift
      value = value.toString().split('e');
      value = Math.round(+(value[0] + 'e' + (value[1] ? (+value[1] + exp) : exp)));

      // Shift back
      value = value.toString().split('e');
      return +(value[0] + 'e' + (value[1] ? (+value[1] - exp) : -exp));
    }

function getColour(prob)
{
  var opacity = 0.34;

  if (prob > 0.8)
    return { stroke: false, color: '#C44531', fillColor: '#C44531', fillOpacity: opacity };
  else if (prob > 0.6)
    return { stroke: false, color: '#E8810C', fillColor: '#E8810C', fillOpacity: opacity };
  else if (prob > 0.4)
    return { stroke: false, color: '#F5E665', fillColor: '#F5E665', fillOpacity: opacity };
  else if (prob > 0.2)
    return { stroke: false, color: '#ADE45B', fillColor: '#ADE45B', fillOpacity: opacity };
  else
    return { stroke: false, color: '#92CA55', fillColor: '#92CA55', fillOpacity: opacity };
}

function getTrainItemColour()
{
  return { color: '#000000', fillColor: '#000000', fillOpacity: 1 };
}

function drawTrainingData()
{
  for (var i = 0; i < window.train.length; i++)
  {
    var item = window.train[i];

    var m = L.marker([item[10], item[11]], 150, getTrainItemColour()).addTo(markers);
    // arch,height,distM,distP,distB,countM,countP,countB,e,n,lat,lon
    m.bindPopup(
      "Height: " + Math.round(item[1]) + " m<br />" +
      "distM: " + Math.round(item[2]) + " m<br />" +
      "distP: " + Math.round(item[3]) + " m<br />" +
      "distB: " + Math.round(item[4]) + " m<br />" +
      "countM: " + Math.round(item[5]) + "<br />" +
      "countP: " + Math.round(item[6]) + "<br />" +
      "countB: " + Math.round(item[7]) + "<br />"
    );
  }
}

function drawData()
{
  for (var i = 0; i < window.data.length; i++)
  {
    var item = window.data[i];

    var circle = L.circle([item[2], item[3]], 1250, getColour(item[9])).addTo(markers);
    // distM,distP,distB,countP,pred
    circle.bindPopup(
      "Probability: " + round(item[9], 2) + "<br />"
      + "Height: " + window.data[i][4] + " m<br />" +
      "distM: " + Math.round(item[5]) + " m<br />" +
      "distP: " + Math.round(item[6]) + " m<br />" +
      "distB: " + Math.round(item[7]) + " m<br />" +
      "countP: " + Math.round(item[8]) + "<br />"
      );
  }
}

function refreshMap()
{
  if (map != undefined)
    L.Util.requestAnimFrame(map.invalidateSize, map, false, map._container);
}

// Actions
$("#action-show-training").click(function(){
  markers.clearLayers();
  drawTrainingData();
  refreshMap();
});

$("#action-show-prediction").click(function(){
  markers.clearLayers();
  drawData();
  refreshMap();
});

$("#action-show-both").click(function(){
  markers.clearLayers();
  drawTrainingData();
  drawData();
  refreshMap();
});

$("#action-show-prediction").click();
