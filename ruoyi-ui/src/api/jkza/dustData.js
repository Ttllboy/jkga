import request from '@/utils/request'

// 查询扬尘设备数据列表
export function listDustData(query) {
  return request({
    url: '/jkza/dustData/list',
    method: 'get',
    params: query
  })
}

// 查询扬尘设备数据详细
export function getDustData(id) {
  return request({
    url: '/jkza/dustData/' + id,
    method: 'get'
  })
}

// 新增扬尘设备数据
export function addDustData(data) {
  return request({
    url: '/jkza/dustData',
    method: 'post',
    data: data
  })
}

// 修改扬尘设备数据
export function updateDustData(data) {
  return request({
    url: '/jkza/dustData',
    method: 'put',
    data: data
  })
}

// 删除扬尘设备数据
export function delDustData(id) {
  return request({
    url: '/jkza/dustData/' + id,
    method: 'delete'
  })
}
