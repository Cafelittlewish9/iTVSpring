// Morris.js Charts sample data for SB Admin template

$(function() {


    // Donut Chart
	Morris.Donut({
		element : 'donut',
		data : [ {
			label : "Final FantasyⅦ",
			value : 12
		}, {
			label : "Final Fantasy Type-Zero",
			value : 30
		}, {
			label : "Kingdom Hearts",
			value : 20
		} ],
		resize : true
	});
//    Morris.Donut({
//        element: 'morris-donut-chart',
//        data: [{
//            label: "Download Sales",
//            value: 12
//        }, {
//            label: "In-Store Sales",
//            value: 30
//        }, {
//            label: "Mail-Order Sales",
//            value: 20
//        }],
//        resize: true
//    });



});
