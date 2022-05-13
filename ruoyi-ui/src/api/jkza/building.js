import request from '@/utils/request'

// 查询工地信息列表
export function listBuilding(query) {
  return request({
    url: '/jkza/building/list',
    method: 'get',
    params: query
  })
}

// 查询工地信息详细
export function getBuilding(id) {
  return request({
    url: '/jkza/building/' + id,
    method: 'get'
  })
}

// 新增工地信息
export function addBuilding(data) {
  return request({
    url: '/jkza/building',
    method: 'post',
    data: data
  })
}

// 修改工地信息
export function updateBuilding(data) {
  return request({
    url: '/jkza/building',
    method: 'put',
    data: data
  })
}

// 删除工地信息
export function delBuilding(id) {
  return request({
    url: '/jkza/building/' + id,
    method: 'delete'
  })
}

//查询所有街道名称
export function listStreetNames(){
  return request({
    url:'/jkza/street/listStreetNames',
    method:'post'
  })
}

