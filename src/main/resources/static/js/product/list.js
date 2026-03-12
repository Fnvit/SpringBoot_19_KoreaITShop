const viewSizeSelect = document.getElementById('view-size-select');
const params = {
    'page': null,
    'size': null,
    'category': null
}

init();

function init(){
    get_pagination_parameters();
    viewSizeSelect.value = params.size;
}

function get_pagination_parameters() {
    const search = location.search.substring(1); // ?viewSize=10&page=1
    for(const item of search.split('&')){
        const [key, value] = item.split('=');
        if(key in params){
            params[key] = value;
        }
    }
}

viewSizeSelect.onchange = () => {
    const size = viewSizeSelect.value;
    let url = `/product/list?`;
    for(const key in params){
        if(key === 'size') {
            url += `size=${size}&`;
            continue;
        }
        params[key] ? url += `${key}=${params[key]}&` : null;
    }
    location.href = url;
}
