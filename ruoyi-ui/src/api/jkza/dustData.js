import request from '@/utils/request'

// 查询扬尘数据bd列表
export function listDustData(query) {
  return request({
    url: '/jkza/dustData/list',
    method: 'get',
    params: query
  })
}

// 查询扬尘数据bd详细
export function getDustData(id) {
  return request({
    url: '/jkza/dustData/' + id,
    method: 'get'
  })
}

// 新增扬尘数据bd
export function addDustData(data) {
  return request({
    url: '/jkza/dustData',
    method: 'post',
    data: data
  })
}

// 修改扬尘数据bd
export function updateDustData(data) {
  return request({
    url: '/jkza/dustData',
    method: 'put',
    data: data
  })
}

// 删除扬尘数据bd
export function delDustData(id) {
  return request({
    url: '/jkza/dustData/' + id,
    method: 'delete'
  })
}
