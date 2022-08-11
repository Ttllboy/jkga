import request from '@/utils/request'

// 查询塔吊预警列表
export function listGdTower(query) {
  return request({
    url: '/jkza/gdTower/list',
    method: 'get',
    params: query
  })
}

// 查询塔吊预警详细
export function getGdTower(id) {
  return request({
    url: '/jkza/gdTower/' + id,
    method: 'get'
  })
}

// 新增塔吊预警
export function addGdTower(data) {
  return request({
    url: '/jkza/gdTower',
    method: 'post',
    data: data
  })
}

// 修改塔吊预警
export function updateGdTower(data) {
  return request({
    url: '/jkza/gdTower',
    method: 'put',
    data: data
  })
}

// 删除塔吊预警
export function delGdTower(id) {
  return request({
    url: '/jkza/gdTower/' + id,
    method: 'delete'
  })
}
