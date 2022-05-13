import request from '@/utils/request'

// 查询事件处理列表
export function listStIncidentProcess(query) {
  return request({
    url: '/jkza/stIncidentProcess/list',
    method: 'get',
    params: query
  })
}

// 查询事件处理详细
export function getStIncidentProcess(id) {
  return request({
    url: '/jkza/stIncidentProcess/' + id,
    method: 'get'
  })
}

// 新增事件处理
export function addStIncidentProcess(data) {
  return request({
    url: '/jkza/stIncidentProcess',
    method: 'post',
    data: data
  })
}

// 修改事件处理
export function updateStIncidentProcess(data) {
  return request({
    url: '/jkza/stIncidentProcess',
    method: 'put',
    data: data
  })
}

// 删除事件处理
export function delStIncidentProcess(id) {
  return request({
    url: '/jkza/stIncidentProcess/' + id,
    method: 'delete'
  })
}
