import request from '@/utils/request'

// 查询预警占比列表
export function listStWarnProportion(query) {
  return request({
    url: '/jkza/stWarnProportion/list',
    method: 'get',
    params: query
  })
}

// 查询预警占比详细
export function getStWarnProportion(id) {
  return request({
    url: '/jkza/stWarnProportion/' + id,
    method: 'get'
  })
}

// 新增预警占比
export function addStWarnProportion(data) {
  return request({
    url: '/jkza/stWarnProportion',
    method: 'post',
    data: data
  })
}

// 修改预警占比
export function updateStWarnProportion(data) {
  return request({
    url: '/jkza/stWarnProportion',
    method: 'put',
    data: data
  })
}

// 删除预警占比
export function delStWarnProportion(id) {
  return request({
    url: '/jkza/stWarnProportion/' + id,
    method: 'delete'
  })
}
